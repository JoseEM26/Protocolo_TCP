# Proyecto: Chat con Protocolo TCP (Sockets en Java)

Este proyecto es una aplicación de mensajería (Chat) cliente-servidor construida en Java utilizando **Sockets TCP**. Está dividido en dos componentes principales gestionados mediante Maven: el **Servidor** (`chat-server`) y el **Cliente** (`chat-client`).

Ambas aplicaciones cuentan con interfaces gráficas desarrolladas con Java Swing para facilitar la interacción de los usuarios.

## Estructura del Proyecto

El repositorio contiene dos proyectos Maven separados:
* `chat-server`: Contiene la lógica del servidor que se queda esperando las conexiones de los clientes. Además, el servidor cuenta con una interfaz que permite visualizar los mensajes recibidos y enviar respuestas.
* `chat-client`: Contiene la lógica del cliente que se conecta a un servidor mediante su dirección (localhost) y un puerto específico.

## Flujo de Funcionamiento

### 1. El Servidor (`chat-server`)
1. Al ejecutar el servidor, aparece una ventana inicial (`DlgConf`) solicitando un **puerto**. El puerto por defecto es `5000`.
2. Una vez aceptado el puerto, se abre la interfaz gráfica principal (`FrmChatServer`).
3. En segundo plano, la clase `BaseChatServer` inicializa el `ServerSocket` y se queda a la espera (`accept()`) de que un cliente se conecte.
4. Cuando un cliente se conecta, se inicializan los flujos de entrada (`BufferedReader`) y salida (`PrintWriter`) de datos.
5. El servidor cuenta con un bucle infinito en un hilo para leer permanentemente los mensajes entrantes mediante el `BufferedReader`.
6. Si el servidor desea enviar un mensaje, obtiene el texto de la interfaz gráfica y lo envía a través del `PrintWriter` añadiendo el prefijo "Servidor: ".

### 2. El Cliente (`chat-client`)
1. Al ejecutar el cliente, se abre la interfaz gráfica principal (`FrmChatClient`).
2. La clase `ChatClient` se encarga de crear el `Socket` que intenta conectarse automáticamente al host `localhost` a través del puerto `5000` (que debe coincidir con el del servidor).
3. Una vez establecida la conexión, de forma idéntica al servidor, se establecen los flujos de lectura y escritura.
4. El cliente se queda dentro de un bucle infinito recibiendo los mensajes del servidor para mostrarlos en el área de texto principal.
5. Cuando el cliente escribe un mensaje y presiona "Enviar", el método `sendMessage()` extrae el texto, añade "Cliente: " y lo manda por la red usando `PrintWriter`.

## Descripción de los Métodos Principales

### En el Servidor (`BaseChatServer`)
* `setLookAndFeel()`: Ajusta el diseño visual de la aplicación para que se asemeje a las ventanas nativas del sistema operativo.
* `setPort()`: Llama a un diálogo de configuración que permite elegir un puerto. Si el usuario cancela, el programa se cierra.
* `addEvents()`: Asigna las acciones (listeners) a los botones y campos de texto de la interfaz gráfica (como presionar "Enter" para enviar).
* `initServer()`: Es el núcleo del servidor. Instancia `ServerSocket`, espera al cliente con `accept()`, inicializa los flujos e ingresa en el ciclo infinito leyendo `in.readLine()`.
* `sendMessage()`: Toma el contenido de la caja de texto gráfica y lo imprime hacia el cliente mediante `out.println()`.

### En el Cliente (`ChatClient`)
* `setLookAndFeel()`: Cambia la apariencia de la ventana al estilo nativo.
* `addEvents()`: Configura y hace visible el frame principal y enlaza la acción de enviar al método `sendMessage()`.
* `initClient()`: Trata de conectar un `Socket` al servidor dado un *host* y un *port*. Luego, lee continuamente lo que el servidor envía y lo muestra en pantalla.
* `sendMessage()`: Captura lo que escribió el usuario y lo transmite a través del `PrintWriter` hacia el servidor.

### Clases de la Interfaz (FrmChatServer, FrmChatClient, DlgConf)
Estas clases extienden `JFrame` o `JDialog` y su propósito principal es configurar los elementos visuales (`JPanel`, `JTextField`, `JButton`, `JTextArea`) mediante un layout determinado.
A destacar:
* `addMessage(String message)`: Toma el texto previo del área de chat de la ventana y le concatena el nuevo mensaje, simulando así un historial de chat en cascada.

## Cómo ejecutarlo
1. Ejecuta primero la clase principal `App` del proyecto **chat-server**.
2. Acepta el puerto e inicializa el servidor.
3. Ejecuta la clase principal `App` del proyecto **chat-client**.
4. ¡Inicia a enviar mensajes entre las ventanas de cliente y servidor!
