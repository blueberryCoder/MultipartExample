package com.blueberry.multipart.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Administrator on 7/6/2017.
 */
public class FileUtil {

    static {
        File file = new File("files");
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void saveFile(MultipartFile file) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("files", file.getOriginalFilename()));
            in = file.getInputStream();

            int len = -1;
            byte[] bytes = new byte[8192];
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(in);
            close(out);
        }
    }

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveInputStream(InputStream in) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("files", "binary"));
            int len = -1;
            byte[] bytes = new byte[8192];
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(in);
            close(out);
        }
    }
}


