![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Evaluación Sumativa 3 – Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto
- **Nombre completo:** Orlando Ubilla Orellana
- **Sección:** Modalidad Online
- **Carrera:** Analista programador computacional
- **Sede:** Online

---

## 📘 Descripción general del sistema
Este proyecto corresponde a la Evaluación Sumativa 3 *"Integrando lógica de negocio con la gestión de datos."*.<br>
Utilizando la programación orientada a objetos junto con la implementación de operaciones CRUD en una base de datos relacional, aplicando técnicas de acceso seguro mediante JDBC.
<br>
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
    database name    = speedfast_db
    database user    = root
    database passwd  = bmxkdhiu1234

````

y una vez creada, dentro correr el Script para crear las tablas requeridas:

```plaintext
    CREATE TABLE repartidores (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL
    );

    CREATE TABLE pedidos (
        id INT AUTO_INCREMENT PRIMARY KEY,
        direccion VARCHAR(100) NOT NULL,
        tipo ENUM('COMIDA','ENCOMIENDA','EXPRESS'),
        estado ENUM('PENDIENTE','EN_REPARTO','ENTREGADO')
        -- COMIDA | ENCOMIENDA | EXPRESS
        -- PENDIENTE | EN_REPARTO | ENTREGADO
    );

    CREATE TABLE entregas (
        id INT AUTO_INCREMENT PRIMARY KEY,
        id_pedido INT NOT NULL,
        id_repartidor INT NOT NULL,
        fecha DATE NOT NULL,
        hora TIME,
        FOREIGN KEY (id_pedido) REFERENCES pedidos(id),
        FOREIGN KEY (id_repartidor) REFERENCES repartidores(id)
    );

````

---



## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

    ```bash
        git clone https://github.com/orlando-ubilla-duoc/POO2.git
    ```
2. ingresar a directorio correspondiente a actividad de la semana 6

    ```bash
        cd semana8/SpeedFastApp/
    ```

3. Desde la raiz del proyecto, compilar via maven.

    ```bash
        mvn clean compile
    ```

    Luego de compilar, ejecutar:
    ```bash
        mvn exec:java -Dexec.mainClass="com.duoc.speedfastapp.Main"
    ```

---

**Repositorio GitHub:** \[https://github.com/orlando-ubilla-duoc/POO2/tree/master/semana8]

**Fecha de entrega:** \[05/10/2026]