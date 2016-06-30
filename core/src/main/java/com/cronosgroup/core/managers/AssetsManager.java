package com.cronosgroup.core.managers;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jorgesanmartin on 1/28/16.
 */
public class AssetsManager {

    public static String getAssetFromFilePath(Context context, String filename) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }
}
