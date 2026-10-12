![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Evaluación Final Transversal – Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto
- **Nombre completo:** Orlando Ubilla Orellana
- **Sección:** Modalidad Online
- **Carrera:** Analista programador computacional
- **Sede:** Online

---

## 📘 Descripción general del sistema
Este proyecto corresponde a la Evaluación Final Transversal<br>
.
<br>
.


---

## 🧱 Estructura general del proyecto

```plaintext
📁 src/
├── config/         # Configuracion y checkeo de conexion a bbdd
├── controller/     # Controladores
├── dao/            # Clases que gestionan las operaciones a la bbdd
├── model/          # Modelo de dominio
└── view/           # Vistas de ventanas

````

---

## Configuracion BBDD

Se debe crear una base de datos MySql con los siguientes datos:

```plaintext
    database address = localhost
    service port     = 3306
    database name    = biblioteca
    database user    = root
    database passwd  = bmxkdhiu1234

````

y una vez creada, dentro correr el Script para crear las tablas requeridas:

```plaintext
📁 BibliotecaEscolar/
└─── resources/
  ├── PRY2203_EFT_S9_Script_crea_tablas_biblioteca.sql     # Creacion de tablas
  └── PRY2203_EFT_S9_Script_poblado_tablas_biblioteca.sql  # Poblamiento de datos

````

---


## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

    ```bash
        git clone https://github.com/orlando-ubilla-duoc/POO2.git
    ```
2. ingresar a directorio correspondiente a actividad de la semana 9

    ```bash
        cd semana9/BibliotecaEscolar/
    ```

3. Desde la raiz del proyecto, compilar via maven.

    ```bash
        mvn clean compile
    ```

    Luego de compilar, ejecutar:
    ```bash
        mvn exec:java -Dexec.mainClass="com.duoc.bibliotecapp.Main"
    ```

---

**Repositorio GitHub:** \[https://github.com/orlando-ubilla-duoc/POO2/tree/master/semana9]

**Fecha de entrega:** \[11/10/2026]