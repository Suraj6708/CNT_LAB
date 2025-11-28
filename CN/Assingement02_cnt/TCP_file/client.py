# tcp_file_client.py
import socket

HOST = '127.0.0.1'
PORT = 8080
BUFFER_SIZE = 1024

# Create TCP socket
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((HOST, PORT))

# Open file to write received data
with open("received.txt", "wb") as f:
    while True:
        data = client_socket.recv(BUFFER_SIZE)
        if not data:
            break
        f.write(data)

print("File received successfully.")

client_socket.close()
