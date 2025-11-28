import socket

HOST = '127.0.0.1'
PORT = 8080
BUFFER_SIZE = 1024

# Create TCP socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen(1)

print(f"Server listening on {HOST}:{PORT}...")

conn, addr = server_socket.accept()
print(f"Connected by {addr}")

# Open file to send
filename = "send.txt"
with open(filename, "rb") as f:
    while True:
        bytes_read = f.read(BUFFER_SIZE)
        if not bytes_read:
            break  
        conn.sendall(bytes_read)

print("File sent successfully.")

conn.close()
server_socket.close()
