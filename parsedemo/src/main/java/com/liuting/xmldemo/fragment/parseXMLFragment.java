package com.liuting.xmldemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuting.xmldemo.R;
import com.liuting.xmldemo.bean.DataInfo;
import com.liuting.xmldemo.utils.FileUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

/**
 * Package:com.liuting.xmldemo.fragment
 * author:liuting
 * Date:2017/4/6
 * Desc:解析 XML
 */

public class parseXMLFragment extends BaseFragment {
    private int type;//类型
    private TextView textView;//解析结果
    public static final int PULL_PARSE_TYPE = 0;//pull方法解析
    public static final int SAX_PARSE_TYPE = 1;//SAX方法解析
    public static final int DOM_PARSE_TYPE = 2;//pull方法解析
    private List<DataInfo> list;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PULL_PARSE_TYPE:
                case SAX_PARSE_TYPE:
                case DOM_PARSE_TYPE:
                    StringBuffer stringBuffer = new StringBuffer();
                    for(int i = 0; i<list.size();i++){
                        stringBuffer.append("id is ");
                        stringBuffer.append(list.get(i).getId());
                        stringBuffer.append("\n name is ");
                        stringBuffer.append(list.get(i).getName());
                        stringBuffer.append("\n version is ");
                        stringBuffer.append(list.get(i).getVersion());
                        stringBuffer.append("\n\n");
                    }
                    textView.setText(stringBuffer.toString());
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * @param type
     * @return
     */
    public static parseXMLFragment newInstance(int type) {
        parseXMLFragment fragment = new parseXMLFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parse_xml_layout, null);
        type = getArguments().getInt("type");
        isPrepared = true;
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    public void initView(View view) {
        textView = (TextView) view.findViewById(R.id.parse_xml_tv_result);
//        doRequest();
        readLocalFile();
    }

    /**
     * 读取本地数据
     */
    private void readLocalFile(){
        String response = FileUtils.readFile("get_data.xml");
        list = new ArrayList<>();
        switch (type) {
            case PULL_PARSE_TYPE://pull 方法解析
                parseXMLWithPull(response);
                handler.sendEmptyMessage(PULL_PARSE_TYPE);
                break;
            case SAX_PARSE_TYPE://SAX 方法解析
                parseXMLWithSAX(response);
                handler.sendEmptyMessage(SAX_PARSE_TYPE);
                break;
            case DOM_PARSE_TYPE://DOM 方法解析
                parseXMLWithDOM(response);
                handler.sendEmptyMessage(DOM_PARSE_TYPE);
                break;
        }
    }


    /**
     * 从网络获取数据
     */
    private void doRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://10.0.2.2:8081/files/get_data.xml");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        list = new ArrayList<>();
                        switch (type) {
                            case PULL_PARSE_TYPE://
                                parseXMLWithPull(response);
                                handler.sendEmptyMessage(PULL_PARSE_TYPE);
                                break;
                            case SAX_PARSE_TYPE://
                                parseXMLWithSAX(response);
                                handler.sendEmptyMessage(SAX_PARSE_TYPE);
                                break;
                            case DOM_PARSE_TYPE://
                                parseXMLWithDOM(response);
                                handler.sendEmptyMessage(DOM_PARSE_TYPE);
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        } else {
            doRequest();
        }
    }

    /**
     * pull 方法解析 XML
     * PULL解析器的运行方式和SAX类似，都是基于事件的模式。PULL解析器小巧轻便，解析速度快，简单易用。
     *
     * @param xmlData 获取的数据
     */
    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析某个点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    //完成解析某个点
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.e("TAG_Pull", "id is " + id + "name is " + name + "version is " + version);
                            DataInfo dataInfo = new DataInfo();
                            dataInfo.setId(id);
                            dataInfo.setName(name);
                            dataInfo.setVersion(version);
                            list.add(dataInfo);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SAX 方法解析 xml
     * SAX解析器的优点是解析速度快，占用内存少。
     *
     * @param xmlData 获取的数据
     */
    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            xmlReader.setContentHandler(handler);
            //开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DOM 方法解析 xml
     * DOM在内存中以树形结构存放，因此检索和更新效率会更高。但是对于特别大的文档，解析和加载整个文档将会很耗资源。
     *
     * @param xmlData
     */
    private void parseXMLWithDOM(String xmlData) {
        String id = "";
        String name = "";
        String version = "";
        // 创建DOM工厂对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            // DocumentBuilder 对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 获取文档对象
            Document document = builder.parse(new InputSource(new ByteArrayInputStream(xmlData.getBytes("utf-8"))));
            // 获取文档对象的 root
            Element root = document.getDocumentElement();
            // 获取apps根节点中所有的 app节点对象
            NodeList appNodes = root.getElementsByTagName("app");
            // 遍历所有的 app节点
            for (int i = 0; i < appNodes.getLength(); i++) {
                DataInfo dataInfo = new DataInfo();
                Element appNode = (Element) appNodes.item(i); // 具体的 app 节点
                // 获取该节点下面的所有字节点
                NodeList appChildNodes = appNode.getChildNodes();
                // 获取子节点
                for (int index =0; index < appChildNodes.getLength(); index++) {
                    Node node = appChildNodes.item(index);
                    // 判断node节点是否是元素节点
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        //把节点转换成元素节点
                        Element element = (Element) node;
                        //判断元素节点是否是 id 元素节点
                        if ("id".equals(element.getNodeName())) {
                            dataInfo.setId(element.getFirstChild()
                                    .getNodeValue().toString().trim());
                        } else if ("name".equals(element.getNodeName())) {//判断是否是 name 节点
                            dataInfo.setName(element.getFirstChild()
                                    .getNodeValue().toString().trim());
                        } else if ("version".equals(element.getNodeName())) {//判断是否是 name 节点
                            dataInfo.setVersion(element.getFirstChild()
                                    .getNodeValue().toString().trim());
                        }
                    }
                }
                list.add(dataInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ContentHandler extends DefaultHandler {
        private String nodeName;
        private StringBuilder id;
        private StringBuilder name;
        private StringBuilder version;

        @Override
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nodeName = localName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if ("id".equals(nodeName)) {
                id.append(ch, start, length);
            } else if ("name".equals(nodeName)) {
                name.append(ch, start, length);
            } else if ("version".equals(nodeName)) {
                version.append(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("app".equals(localName)) {
                Log.e("TAG_SAX", "id is " + id.toString().trim() + "name is " + name.toString().trim() + "version is " + version.toString().trim());
                DataInfo dataInfo = new DataInfo();
                dataInfo.setId(id.toString().trim());
                dataInfo.setName(name.toString().trim());
                dataInfo.setVersion(version.toString().trim());
                list.add(dataInfo);

                //最后要将 StringBuilder 清空
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {
        }
    }

}
