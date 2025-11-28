# udp_scientific_client.py
import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_address = ('localhost', 12345)

while True:
    message = input("Enter expression (or 'exit' to quit): ")
    if message.lower() == "exit":
        break

    client_socket.sendto(message.encode(), server_address)
    data, _ = client_socket.recvfrom(1024)
    print("Result:", data.decode())

client_socket.close()
