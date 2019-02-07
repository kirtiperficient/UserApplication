package com.perfi.UserApplication.homecontroller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {

	@RequestMapping("/all")
	public @ResponseBody ArrayList<RegisterModel> getAllUser(
			@RequestParam(value = "name", required = false) String name) {
		RegisterModel model = new RegisterModel();
		ArrayList<RegisterModel> modellist = readCSVfile(name);
		return modellist;
	}

	public ArrayList<RegisterModel> readCSVfile(String name) {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		ArrayList<RegisterModel> modellist = new ArrayList<>();
		BufferedReader reader;
		StringTokenizer st = null;
		try {
			reader = new BufferedReader(new FileReader(s + "/log/user.csv"));
			String strLine = "";
			int linenumber = 0;
			while ((strLine = reader.readLine()) != null) {
				if (linenumber > 0) {
					// break comma separated line using ","
					st = new StringTokenizer(strLine, ",");
					RegisterModel model = new RegisterModel();
					int i = 0;
					while (st.hasMoreTokens()) {
						if (i == 0) {
							model.setEmail(st.nextToken());
						}
						if (i == 1) {
							model.setPsw(st.nextToken());
						}
						if (i == 2) {
							model.setPswrepeat(st.nextToken());
						}
						if (i == 3) {
							model.setRemember(st.nextToken());
						}

						i++;
					}

					modellist.add(model);
					if (name != null && model.getEmail().equals(name)) {
						return modellist;
					}
				}
				linenumber++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String strLine = "";
		return modellist;
	}

}
