package cc.libqrscanner;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by cx on 9/20/16.
 */

public class Constans {

    /**
     * 阻塞列表,用于存储扫描结果
     */
    public static ArrayBlockingQueue<String> sArrayBlockingQueue = new ArrayBlockingQueue<String>(1);

}
