package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.domain.UploadImageFile;
import net.flower.ixmsxms_server.domain.User;
import org.apache.velocity.tools.generic.DateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hyeyoungkang on 2014. 7. 17..
 */
@Service
public class UploadService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Object upload(HttpServletRequest request, UploadImageFile uploadImageFile, User user) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<MultipartFile> files = uploadImageFile.getFiles();
        HttpSession session = request.getSession();

//      폴더 경로
//      /Users/hyeyoungkang/privacy/ixmsxms_server/target/ixmsxms_server/uploads/{userId}/{datetime}
        this.logger.debug("@@@ Upload  : " + files);

        if( !files.isEmpty() ) {
            List<String> fileNames = new ArrayList<String>();
            List<String> filePaths = new ArrayList<String>();
//            String root = "/Users/hyeyoungkang/privacy/uploads";
            // todo : folder경로는 추후 재 설정하기
            String root = session.getServletContext().getRealPath("/uploads");
            Long userId = user.getUserId();

            this.logger.debug("@@@ Path :" + files.size());
            System.out.println( files );

            if (files != null && files.size()>0 ) {
                for (MultipartFile multipartFile : files) {
                    String fileName = multipartFile.getOriginalFilename();
                    this.logger.debug("@@@ getOriginalFilename : " + fileName);
                    if( fileName !="") {
                       DateTool date = new DateTool();

                        String dateFileName = date.get("yyyyMdHms");
                        String dirPath = root + "/" + Long.toString(userId) ;

                        this.logger.debug("@@@ dateFileName : " + dateFileName);
                        this.logger.debug("@@@ Upload filename : " + multipartFile.getOriginalFilename());
                        this.logger.debug("@@@ Path :" + dirPath);
                        this.logger.debug("@@@ Path : toto : folder경로는 다시 설정해 주세요!! ");

                        File f = new File(dirPath);
                        if( !f.exists() ){
                            f.mkdir();
                        }
                        File newFile = new File(dirPath + "/" + dateFileName);
                        this.logger.debug("@@@ Upload path name : " + newFile.getAbsolutePath());
                        multipartFile.transferTo(newFile);
                        fileNames.add(fileName);
                        filePaths.add(newFile.getAbsolutePath());
                    }
                }
            }
            map.put("files",  fileNames );
            map.put("paths",  filePaths );
        }else{
            map.put("files", "파일이 비었네요.");
        }
        return map;
    }
}
//http://www.oraclejavanew.kr/bbs/board.php?bo_table=LecSpring&wr_id=295
//http://winmargo.tistory.com/102
//http://everlikemorning.tistory.com/entry/Spring-%EC%97%85%EB%A1%9C%EB%93%9C-%EA%B5%AC%ED%98%84