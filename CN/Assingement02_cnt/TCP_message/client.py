# tcp_message_client.py
import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)   # (using ipv4 , tcp not udp)
client_socket.connect(( '127.0.0.1' , 8080))

# Send message to server
message = "Hello from client!"
client_socket.sendall(message.encode())

# Receive reply
data = client_socket.recv(1024).decode()
print(f"Server says: {data}")

client_socket.close()
