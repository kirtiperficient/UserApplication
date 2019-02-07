package com.perfi.UserApplication.homecontroller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "email,password,repeat password,remember";

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegister(@ModelAttribute RegisterModel registerModel) {
		System.out.println("in register=>" + registerModel.getEmail());
		writeCsvFile(registerModel);
		return "redirect:index.html";
	}

	public void writeCsvFile(RegisterModel model) {

		// Create a new list of student objects
		ArrayList<RegisterModel> userlist = new ArrayList<RegisterModel>();
		userlist.add(model);

		FileWriter fileWriter = null;

		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			System.out.println("Current relative path is: " + s);

			fileWriter = new FileWriter(s + "/log/user.csv", true);

			BufferedReader reader = new BufferedReader(new FileReader(s + "/log/user.csv"));

			String strLine = "";

			if ((strLine = reader.readLine()) == null) {
				// Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());

				// Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			// Write a new student object list to the CSV file
			for (RegisterModel user : userlist) {
				fileWriter.append(String.valueOf(user.getEmail()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(user.getPsw());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(user.getPswrepeat());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(user.getRemember());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
				
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}

	}

}
