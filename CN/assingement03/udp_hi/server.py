import socket

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

server_socket.bind(('localhost', 12345))
print("UDP Server is listening...")

while True:
    data, addr = server_socket.recvfrom(1024)  # buffer size 1024 bytes
    print(f"Received from {addr}: {data.decode()}")
    server_socket.sendto(b"Message received", addr)
