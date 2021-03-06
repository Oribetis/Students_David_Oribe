package com.netmind.presentation;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netmind.business.StudentBlImpl;
import com.netmind.business.contracts.StudentBl;
import com.netmind.common.model.EnumStudent;
import com.netmind.common.model.Student;
import com.netmind.dao.StudentDaoImpl;
import com.netmind.dao.contracts.StudentDao;

public class StudentConsole {

	@SuppressWarnings("static-access")
	public static void selectOperation() {
		Scanner scanner = new Scanner(System.in);
		StudentDao studentDao = new StudentDaoImpl();
		StudentBl studentBl = new StudentBlImpl(studentDao);
		EnumStudent enumStudent = null;
		int option;

		do {
			showPrincipalMenu();
			// The nextInt() method does not deal with the EOL token,
			// while nextLine() does.
			option = Integer.parseInt(scanner.nextLine());
			enumStudent = EnumStudent.fromValue(option);

			switch (enumStudent) {
			case ADD_STUDENT:
				Student student = new Student();
				addNewStudent(student, scanner);
				try {
					studentBl.add(student);
					studentBl.addToJsonFile(student);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;
			case SELECT_STUDENT:
				selectStudent(scanner);
				break;
			case DELETE_STUDENT:
				System.out.println("Introduce el id del estudiante a eliminar: ");
				int idToRemove = Integer.parseInt(scanner.nextLine());
				try {
					studentBl.removeFromJsonFile(idToRemove);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case UPDATE_STUDENT:
				break;
			case EXIT:
				System.out.println("Good Bye!!");
				break;
			default:
				break;
			}
		} while (option != enumStudent.EXIT.value());
		scanner.close();
	}

	private static void showPrincipalMenu() {
		System.out.println("?Qu? opci?n quiere seleccionar?");
		System.out.println("1.Agregar un nuevo estudiante");
		System.out.println("2.Seleccionar estudiante");
		System.out.println("3.Eliminar estudiante");
		System.out.println("4.Actualizar estudiante");
		System.out.println("5.Salir del programa");
	}
	private static void selectStudent(Scanner scanner) {
		try {
			@SuppressWarnings("deprecation")
			JsonParser parser = new JsonParser();
            @SuppressWarnings("deprecation")
			Object obj = parser.parse(new FileReader("alumno.json"));
            JsonArray json = (JsonArray) obj;
            for (int i = 0; i < json.size(); i++) {
            	System.out.println(json.get(i).toString());
            }
            System.out.println();
            System.out.println("Introduce la id del alumno a seleccionar");
            int idToSelect = Integer.parseInt(scanner.nextLine());
            System.out.println();
            for (int i = 0; i < json.size(); i++) {
            	JsonObject object =(JsonObject) json.get(i);
            	if(Integer.parseInt(object.get("idStudent").toString())==idToSelect) {
            		System.out.println("Alumno seleccionado: ");
            		System.out.println(json.get(i).toString());
            	}	
            }
            System.out.println();
            
			}catch(Exception ex) {
				System.out.println("Error :"+ex.getMessage());
			}
	}
	private static void addNewStudent(Student student, Scanner scanner) {
		System.out.println("1.Agregar un nuevo estudiante");
		System.out.println("Introduce nombre: ");
		student.setName(scanner.nextLine());

		System.out.println("Introduce apellidos: ");
		student.setSurname(scanner.nextLine());

		System.out.println("Introduce Fecha de nacimiento (dd-MM-yyyy): ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine(), formatter);

		student.setDateOfBirth(dateOfBirth);
	}
}
