//////Base de datos.
El SGBD usado es MySQL, inicia el servidor web y de base de datos en XAMPP e importa el archivo
"BaseDatosUsada.sql".
En mi servidor local MySQL, uso el puerto 3307, para que no tenga problemas con el MySQL que integra
el propio XAMPP, por lo que hay 2 opciones para que el programa acceda a la base de datos:
	·Cambiar el puerto que usa el XAMPP para el servidor sql a 3307.
	·Acceder al archivo "MotorSQL.java" y cambiar en la variable "url" el puerto al que usa tu
	 servidor sql de XAMPP.



//////Funcionamiento del programa.
En el menú del comienzo seleccionas la acción a tomar y luego la tabla sobre la que vas a ejecutarla.

Al exportar los datos, se crea en la carpeta "Archivos" un nuevo archivo si no existe.

Al importar datos, lee los archivos ya existentes según la tabla que decidamos importar.

En el proceso de importación, comprueba si el nombre del artículo ya existe, y si el DNI del particular
ya existe en la tabla.
En caso de que ya existan, se muestra por consola el mensaje de que el dato ya existe y que la operación
se va a cancelar.

En el caso de las PKs, primero comprobamos si en el XML del articulo que se va a intentar importar contiene
una PK.
En caso de que NO la contenga, se realiza una inserción sin especificar la ID, ya que en la base de
datos las IDs estan registradas como INTs autoincrementados, por lo que se pondrá la siguiente ID que corresponda
en la lista.
En caso de que SI la contenga, se comprobará si esta ID ya existe. Si no existe se realizará la inserción
especificando la ID. Si la la ID ya existe, se cancelará la inserción.

Es importante tener en cuenta que el programa esta hecho de forma que se comprueba primero el nombre o DNI
según que tabla estemos intentando importar, y luego la ID. Es por esto que si intentamos insertar una fila
cuyo nombre/DNI ya existen y la ID no es válida, mandará el mensaje de error por el nombre/DNI, pero se cancelará
la inserción directamente sin comprobar la ID.



//////Actualización 06/11/2003 -> Añadir al programa las tablas "tickets" y "dir_envio".
Al tratar de insertar un ticket comprobamos que la ID no existe, en caso de que ya exista se cancela la inserción
y muestra el aviso de error. Si la ID no existe o no está especificada se hace la inserción correspondiente.

Para las direcciones de envío se comprueba la ID de igual manera, y comprobamos que la calle que se esté intentando
insertar no exista. Tambien se comprueba que el campo "nombre" de la dirección no esté vacío, en caso de que esté
vacío muestra el aviso y se cancela la inserción.