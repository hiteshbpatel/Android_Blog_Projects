package com.vigneshtraining.assignment113;

/**
 * Created by vimadhavan on 4/22/2017.
 */

public interface FileOperationListener {
    void onSDcardChecked(Boolean isReady,String status);
    void onMemoryChecked(long total,long free);
    void onFileOpearionMsg(String msg);
    void onFileRead(String text);
}
