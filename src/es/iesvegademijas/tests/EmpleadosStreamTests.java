package es.iesvegademijas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import es.iesvegademijas.streams.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

class EmpleadosStreamTests {

	@Test
	void testSkeletonDepartamento() {
	
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
		
			
			//TODO STREAMS
			
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	

	@Test
	void testSkeletonEmpleado() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listProd = empHome.findAll();		
						
			//TODO STREAMS
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	@Test
	void testAllDepartamento() {
	
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllEmpleado() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();		
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 1. Lista el código de los departamentos de los empleados, 
	 * eliminando los códigos que aparecen repetidos.
	 */
	@Test
	void test1() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			//
			List<String> lstdep = listEmp.stream()
					.map(e -> e.getDepartamento().getNombre())
					.distinct()
					.collect(toList());		
				
			
			lstdep.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 2. Lista el nombre y apellidos de los empleados en una única columna, convirtiendo todos los caracteres en mayúscula 
	 * 
	 */
	@Test
	void test2() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
						
			//
			List<String> nomApe = listEmp.stream()
					
					.map(e -> e.getNombre().toUpperCase()+", "+ e.getApellido1().toUpperCase()+", "+ e.getApellido2().toUpperCase()+"\n")
					.collect(toList());
				nomApe.forEach(System.out::println);
			
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}

	/**
	 * 3. Lista el código de los empleados junto al nif, pero el nif deberá aparecer en dos columnas, 
	 * una mostrará únicamente los dígitos del nif y la otra la letra.
	 */
	
	@Test
	void test3() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			//
			List<string> list2 = listEmp.stream()
					
					.map (e -> e.getCodigo() + ", " + e.getNif().substring(0 , 7)+ ", "+ e.getNif().substring(8))
					.collect(toList());
			
			list2.forEach(System.out::println);			
			
			
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 4. Lista el nombre de cada departamento y el valor del presupuesto actual del que dispone. 
	 * Para calcular este dato tendrá que restar al valor del presupuesto inicial (columna presupuesto) los gastos que se han generado (columna gastos).
	 *  Tenga en cuenta que en algunos casos pueden existir valores negativos.
	 */
	@Test
	void test4() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			List<String> listPreAct = listDep.stream()
					.map(p -> "nombre" + p.getNombre() + ", Presupuesto Actual: " + (p.getPresupuesto()-p.getGastos()))
					.collect(toList());
			
			listPreAct.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 5. Lista el nombre de los departamentos y el valor del presupuesto actual ordenado de forma ascendente.
	 */
	@Test
	void test5() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			List<String> listPrep = listDep.stream()
					.sorted(comparing(Departamento::getPresupuesto).reversed())
					.map( d -> d.getNombre() + ", "+ d.getPresupuesto())					
					.collect(toList());
			
			listPrep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 6. Devuelve una lista con el nombre y el presupuesto, de los 3 departamentos que tienen mayor presupuesto
	 */
	@Test
	void test6() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			List<String> lisMaxPrep = listDep.stream()
					.sorted(comparing(Departamento::getPresupuesto).reversed())
					.limit(3)
					.map(d -> d.getNombre() + ", " + d.getPresupuesto())
					.collect(toList());
					
			lisMaxPrep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 7. Devuelve una lista con el nombre de los departamentos y el presupesto, de aquellos que tienen un presupuesto entre 100000 y 200000 euros
	 */
	void test7() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			List<Departamento> listbetwen = listDep.stream()
					.filter(d -> d.)
					
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 8. Devuelve una lista con 5 filas a partir de la tercera fila de empleado ordenado por código de empleado. La tercera fila se debe incluir en la respuesta.
	 */
	@Test
	void test8() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			//
			
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 9. Devuelve una lista con el nombre de los departamentos y el gasto, de aquellos que tienen menos de 5000 euros de gastos.
	 * Ordenada de mayor a menor gasto.
	 */
	void test9() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 10. Lista todos los datos de los empleados cuyo segundo apellido sea Díaz o Moreno
	 */
	@Test
	void test10() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			//
			
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 11. Lista los nombres, apellidos y nif de los empleados que trabajan en los departamentos 2, 4 o 5
	 */
	@Test
	void test11() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			//
			
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	
	/**
	 * 12. Devuelve el nombre del departamento donde trabaja el empleado que tiene el nif 38382980M
	 */
	@Test
	void test12() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			//
			
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 13. Devuelve una lista con el nombre de los empleados que tienen los departamentos 
	 * que no tienen un presupuesto entre 100000 y 200000 euros.
	 */
	@Test
	void test13() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			//
			
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 14. Calcula el valor mínimo del presupuesto de todos los departamentos.
	 */
	void test14() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 15. Calcula el número de empleados que hay en cada departamento. 
	 * Tienes que devolver dos columnas, una con el nombre del departamento 
	 * y otra con el número de empleados que tiene asignados.
	 */
	void test15() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 16. Calcula el número total de empleados que trabajan en cada unos de los departamentos que tienen un presupuesto mayor a 200000 euros.
	 */
	void test16() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 17. Calcula el nombre de los departamentos que tienen más de 2 empleados. 
	 * El resultado debe tener dos columnas, una con el nombre del departamento y
	 *  otra con el número de empleados que tiene asignados
	 */
	void test17() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/** 18. Lista todos los nombres de departamentos junto con los nombres y apellidos de los empleados. 
	 * 
	 */
	void test18() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 19. Devuelve la media de empleados que trabajan en los departamentos
	 */
	void test19() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 20. Calcula el presupuesto máximo, mínimo  y número total de departamentos con un solo stream.
	 */
	void test20() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
}
