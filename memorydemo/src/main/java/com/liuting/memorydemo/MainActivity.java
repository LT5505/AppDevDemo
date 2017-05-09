package com.liuting.memorydemo;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.TextView;

/**
 * @ClassName: MainActivity
 * @Package: com.liuting.memorydemo
 * @Description: 内存管理
 * @author: liuting
 * @Date: 2017/5/9 11:17
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvShow;//显示信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * @throws
     * @author: liuting
     * @date: 2017/5/9 11:27
     * @MethodName: initView
     * @Description: 初始化控件
     * @param: []
     * @return: void
     */
    public void initView() {
        tvShow = (TextView) findViewById(R.id.main_tv_show);

        ActivityManager manager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
//        int memory = manager.getMemoryClass();
//        int memory = manager.getLargeMemoryClass();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();//存放内存信息的对象
        manager.getMemoryInfo(memoryInfo);//传入参数，将获得数据保存在memInfo对象中
        //下面获取的值的单位都为字节
        long availMem = memoryInfo.availMem;//可用内存
        boolean lowMemory = memoryInfo.lowMemory;//是否达到最低内存
        long threshold = memoryInfo.threshold;//临界值，到达这个值的时候，进程会被杀死
        long totalMem = memoryInfo.totalMem;//总内存

        //转换为MB
        String availMem_m = Formatter.formatFileSize(this, availMem);
//        tvShow.setText(" availMem: "+availMem+"\n availMem_m："+availMem_m+"\n lowMemory："+lowMemory+"\n threshold："+threshold+"\n totalMem："+totalMem);

        //获取各个进程信息
//        List<ActivityManager.RunningAppProcessInfo> list = manager.getRunningAppProcesses();
//        StringBuffer stringBuffer = new StringBuffer();
//        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
//            int importance = runningAppProcessInfo.importance;//通过给进程标值来给进程分类，越重要的进程，那么数值就越低
//
//            int importanceReasonCode = runningAppProcessInfo.importanceReasonCode;//重要性的原因，也是通过数值去判断
//
//            ComponentName importanceReasonComponent = runningAppProcessInfo.importanceReasonComponent;//重要性原因的组件，返回一个ComponentName的对象
//
//            int importanceReasonPid = runningAppProcessInfo.importanceReasonPid;//其他客户端进程的进程id号，如果没有其他进程返回0
//
//            int lastTrimLevel = runningAppProcessInfo.lastTrimLevel;//提供给ComponentCallbacks2.onTrimMemory(int)方法的参数。上一次提交给进程的内饰水平
//
//            int lru = runningAppProcessInfo.lru;//在一个特殊的进程中，提供更精细密度的衡量值，目前只维护IMPORTANCE_BACKGROUND
//
//            int pid = runningAppProcessInfo.pid;//进程id
//
//            String proName = runningAppProcessInfo.processName;//进程名
//
//            int uid = runningAppProcessInfo.uid;//用户id
//
//            stringBuffer.append(" importance: " + importance + "\n importanceReasonCode：" + importanceReasonCode +
//                    "\n importanceReasonComponent：" + importanceReasonComponent + "\n importanceReasonPid：" +
//                    importanceReasonPid + "\n lastTrimLevel：" + lastTrimLevel + "\n lru：" + lru + "\n pid：" + pid + "\n proName：" + proName
//                    + "\n uid：" + uid + " \n");
//
//        }
//        tvShow.setText(stringBuffer.toString()+"\n list.size："+list.size());

        //获取进程中的错误信息
//        List<ActivityManager.ProcessErrorStateInfo> list2 = manager.getProcessesInErrorState();
//        StringBuffer stringBuffer = new StringBuffer();
//        if (list2 != null) {
//            for(ActivityManager.ProcessErrorStateInfo processErrorStateInfo:list2){
//                int condition = processErrorStateInfo.condition;// 进程进入的条件
//
//                byte[] data = processErrorStateInfo.crashData;// crash数据
//                if (data != null) {
//                    stringBuffer.append("\ncrashData："+data.toString());
//                }else{
//                    stringBuffer.append("\ncrashData："+null);
//                }
//                String longMsg = processErrorStateInfo.longMsg;// 对条件condition的描述
//                stringBuffer.append("\nlongMsg："+longMsg);
//
//                int pid = processErrorStateInfo.pid;// 进程id
//                stringBuffer.append("\npid："+pid);
//
//                String proName = processErrorStateInfo.processName;// 进程名
//                stringBuffer.append("\nproName："+proName);
//
//                String shortMsg = processErrorStateInfo.shortMsg;
//                stringBuffer.append("\nshortMsg："+shortMsg);
//
//                String sTrace = processErrorStateInfo.stackTrace;// 堆栈追踪到的信息
//                stringBuffer.append("\nstackTrace："+sTrace);
//
//                String tag = processErrorStateInfo.tag;// activity名是否与错误有关联
//                stringBuffer.append("\ntag："+tag);
//
//                int uid = processErrorStateInfo.uid;//uid
//                stringBuffer.append("\nuid："+uid);
//
//                int describeContents = processErrorStateInfo.describeContents();// 数据包裹的描述
//                stringBuffer.append("\ndescribeContents："+describeContents);
//            }
//        }
//        tvShow.setText(stringBuffer.toString());

        //获取任务信息RunningTaskInfo，在Android L 之后被抛弃，不能再使用了，
        // 之后是使用List<ActivityManager.AppTask> list = manager.getAppTasks();只限制于5.0之后
//        List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(10);
//        StringBuffer stringBuffer = new StringBuffer();
//        if (list != null) {
//            for (ActivityManager.RunningTaskInfo info : list) {
//                ComponentName baseActivity = info.baseActivity;//任务主activity名
//                CharSequence description = info.description;//任务的描述
//                if(description!=null){
//                    stringBuffer.append("\ndescription: " + description.toString());
//                }else{
//                    stringBuffer.append("\ndescription: null" );
//                }
//                int id = info.id;//任务的id号
//                stringBuffer.append("\nid: " + id);
//
//                int numActivity = info.numActivities;//该任务的activity的数量
//                stringBuffer.append("\nnumActivity: " + numActivity);
//
//                int numRunning = info.numRunning;//当前活动的activity数量
//                stringBuffer.append("\nnumRunning: " + numRunning);
//
//                Bitmap bitmap = info.thumbnail;//缩略图
//                stringBuffer.append("\nbitmap: " + bitmap);
//
//                ComponentName topActivity = info.topActivity;//当前活动activity中处于最顶端的activity
//                stringBuffer.append("\ntopActivity: " + topActivity);
//
//                int content = info.describeContents();//描述文本
//                stringBuffer.append("\ncontent: " + content);
//                stringBuffer.append("================================");
//            }
//            tvShow.setText(stringBuffer.toString());
//        }

    }
}
