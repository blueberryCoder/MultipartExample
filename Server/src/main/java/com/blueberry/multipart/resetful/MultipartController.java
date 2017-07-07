package com.blueberry.multipart.resetful;

import com.blueberry.multipart.entity.PartInfo;
import com.blueberry.multipart.entity.UpLoadResponse;
import com.blueberry.multipart.util.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by Administrator on 7/6/2017.
 */
@RestController
@RequestMapping("/upload")
public class MultipartController {

    /**
     * 单个文件上传。
     * <p>
     * 字段名为 file。
     *
     * @param file 文件
     * @return json
     */
    @RequestMapping(method = RequestMethod.POST, value = "/single")
    public UpLoadResponse singleReceive(@RequestParam("file") MultipartFile file) {
        List<PartInfo> partInfos = new ArrayList<PartInfo>();
        PartInfo partInfo = new PartInfo();
        partInfo.setMediaType(file.getContentType());
        partInfo.setSize(file.getSize());
        partInfos.add(partInfo);

        FileUtil.saveFile(file);

        System.out.println("接受到文件====" + file.getOriginalFilename());
        UpLoadResponse upLoadResponse = new UpLoadResponse();
        upLoadResponse.setPartInfos(partInfos);
        upLoadResponse.setMsg("上传成功");
        return upLoadResponse;
    }

    /**
     * 多文件上传，公用一个字段 "file"
     *
     * @param files 文件
     * @return json
     */
    @RequestMapping(method = RequestMethod.POST, value = "/multi_file")
    public UpLoadResponse multiFileReceive(@RequestParam("file") MultipartFile[] files) {
        List<PartInfo> partInfos = new ArrayList<PartInfo>();

        for (MultipartFile file : files) {
            PartInfo partInfo = new PartInfo();
            partInfo.setSize(file.getSize());
            partInfo.setMediaType(file.getContentType());
            partInfos.add(partInfo);

            FileUtil.saveFile(file);

            System.out.println("接受到文件====" + file.getOriginalFilename());
        }
        UpLoadResponse upLoadResponse = new UpLoadResponse();
        upLoadResponse.setPartInfos(partInfos);
        upLoadResponse.setMsg("上传成功");
        return upLoadResponse;
    }

    /**
     * 文件+文本一起上传，其实和多文件上传一样的。
     * <p>
     * 字段名为 file、text
     *
     * @param file 文件
     * @param text 文本
     * @return json
     */
    @RequestMapping(method = RequestMethod.POST, value = "/multi")
    public UpLoadResponse multiReceive(@RequestParam("file") MultipartFile file,
                                       @RequestParam("text") String text) {


        List<PartInfo> partInfos = new ArrayList<PartInfo>();

        PartInfo partInfo = new PartInfo();
        partInfo.setMediaType(file.getContentType());
        partInfos.add(partInfo);
        partInfo.setSize(file.getSize());
        // 保存文件
        FileUtil.saveFile(file);

        System.out.println("接受到文件====" + file.getOriginalFilename());

        PartInfo partInfo1 = new PartInfo();
        partInfo.setText(text);
        partInfo.setSize(text.length());
        partInfos.add(partInfo1);

        System.out.println("接受到文本====" + text);

        UpLoadResponse upLoadResponse = new UpLoadResponse();
        upLoadResponse.setPartInfos(partInfos);
        upLoadResponse.setMsg("提交成功");

        return upLoadResponse;
    }

    /**
     * 二进制上传。
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/binary")
    public UpLoadResponse binaryReceive(HttpServletRequest request) {

        String contentType = request.getHeader("Content-Type");

        String size = request.getHeader("Content-Length");
        try {
            InputStream in = request.getInputStream();
            FileUtil.saveInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<PartInfo> partInfos = new ArrayList<PartInfo>();

        PartInfo partInfo = new PartInfo();
        partInfo.setMediaType(contentType + "");
        partInfo.setSize(Integer.parseInt(size));
        partInfos.add(partInfo);
        UpLoadResponse upLoadResponse = new UpLoadResponse();
        upLoadResponse.setPartInfos(partInfos);
        upLoadResponse.setMsg("二进制上传成功");
        return upLoadResponse;
    }
}
