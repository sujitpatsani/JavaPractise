package linkedList;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class comparatorExample {

	public static void main(String[] args) {

		LinkedList<Employee> list = new LinkedList<Employee>();
		list.add(new Employee("Sainath", 2000));
		list.add(new Employee("Ram", 2054));
		list.add(new Employee("Hari", 5000));
		list.add(new Employee("Shyma", 6000));
		
		Collections.sort(list,new MySalary());
		
		for (Employee employee : list) {
			System.out.println(employee.getSalary());
		}

	}
}
	




class MySalary implements Comparator<Employee> {

		@Override
		public int compare(Employee e1, Employee e2) {
			if (e1.getSalary() < e2.getSalary()) {
				return 1;
			} else {
				return -1;

			}
		}

	}
	


	class Employee {

		private String name;
		private int salary;

		Employee(String name, int salary) {
			this.name = name;
			this.salary = salary;
		}

		public String getName() {
			return name;
		}

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Employee [name=" + this.name + ", salary=" + this.salary
					+ "]";
		}

	}


