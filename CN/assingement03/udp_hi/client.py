import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

server_address = ('localhost', 12345)
message = "Hello"

# Send message
client_socket.sendto(message.encode(), server_address)

# Receive response
data, server = client_socket.recvfrom(1024)
print("Server replied:", data.decode())
