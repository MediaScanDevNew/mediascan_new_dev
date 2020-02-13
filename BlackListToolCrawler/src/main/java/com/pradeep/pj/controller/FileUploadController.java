package com.pradeep.pj.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opencsv.CSVReader;
import com.pradeep.pj.crawl.source.FindDomainName;
import com.pradeep.pj.crawl.source.PageSource;
import com.pradeep.pj.crawl.source.SourceLinkExtracter;
import com.pradeep.pj.model.Infringing_source;
import com.pradeep.pj.property.AlbelaLinks;
import com.pradeep.pj.repo.impl.InfringingSourceRepositoryIMPL;

/*
 * Created by pentation/m on 10/02/20
 */
@RestController
public class FileUploadController {
	private static String UPLOADED_FOLDER = "/home/subham/dumps/";
	
	@Autowired
	private InfringingSourceRepositoryIMPL isri;

	@Autowired
	private AlbelaLinks propFile;

	@Autowired
	private PageSource pageSource__c;

	@Autowired
	private FindDomainName domain_name__c;

	@Autowired
	private SourceLinkExtracter srcExtractor__c;
	
	@Autowired
    private HttpServletRequest request;
	
	String keyword = null;
	List<Infringing_source> report = null;
	List<Infringing_source> result = null;
	Infringing_source i_s = null;
	File serverFile = null;
	BufferedOutputStream stream = null;
	CSVReader reader = null;
	String[] line;
	String error = null;
	int link_size = 0;
	int source_link_size = 0;
	String name =null ;
	String uploadRootPath = null;
	File uploadRootDir = null;
	@PostMapping("/uploadCSV")
	public String csvUpload(@RequestParam("file") MultipartFile file,@RequestParam("user_id") int user_id){
		try {
		byte[] bytes = file.getBytes();
		System.out.println("path:--->"+UPLOADED_FOLDER + file.getOriginalFilename());
//		String file_path=UPLOADED_FOLDER + file.getOriginalFilename();
		uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);
        Path path = Paths.get(uploadRootPath);
        Files.write(path, bytes);
        name = file.getOriginalFilename();
        result = new ArrayList<>();
        if (name != null && name.length() > 0) {
        	report = new ArrayList<>();

			try {
				reader = new CSVReader(new FileReader(uploadRootPath));
				while ((line = reader.readNext()) != null) {
					CrawleController crawler=new CrawleController();
					i_s = new Infringing_source();
					keyword = null;
					System.out.println(line[0] + "============= " + line[1]);
					keyword = line[1].trim();
					i_s.setInfringing_link(line[0]);
					// store data on database here projectid shows no of
					// infringing link crawl
					
					System.out.println("prop");
					System.out.println("line[2]:"+line[2].trim());
					if (line[0].toLowerCase().contains(propFile.getTellyNagariLink().toLowerCase())) {
						i_s.setProjectid(crawler.tellynagariLink(line[0].trim(), line[1].trim(), user_id,
								InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(line[2].trim())));
					} else {
						i_s.setProjectid(crawler.linkCrowle(line[0].trim(), line[1].trim(), user_id,
								InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(line[2].trim())));
					}
					i_s.setSearch_keyword(line[1]);
					i_s.setRow_in_use(source_link_size);
					// display result on screen
					result.add(i_s);
					i_s = null;
				}

			} catch (IOException e) {
				error = "either userID or projectID is missing, try again.";
				e.printStackTrace();
			} finally {
				reader.close();
			}

        }
		return "Upload Done";
		} catch (IOException e) {
            e.printStackTrace();
            i_s = null;
			serverFile = null;
			stream = null;
			reader = null;
			line = null;
			name = null;
			keyword = null;
            return "Upload Failed";
        }
	}
}
