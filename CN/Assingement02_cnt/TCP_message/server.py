import socket

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(('127.0.0.1', 8080))  # assign socket to iip
server_socket.listen(1)  # max queue

print(f"Server listening on 8080...")

conn, addr = server_socket.accept()
print(f"Connected by {addr}")

data = conn.recv(1024).decode()
print(f"Client says: {data}")

# Send reply
reply = "Hello from server!"
conn.sendall(reply.encode())

conn.close()
server_socket.close()
