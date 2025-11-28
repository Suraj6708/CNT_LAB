# udp_scientific_server.py
import socket
import math

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_socket.bind(('localhost', 12345))
print("UDP Scientific Calculator Server is listening...")

while True:
    data, addr = server_socket.recvfrom(1024)
    expr = data.decode().strip()

    try:
        expr = expr.replace("^", "**")

        allowed_names = {name: getattr(math, name) for name in dir(math) if not name.startswith("__")}
        allowed_names.update({"abs": abs, "round": round})

        result = eval(expr, {"__builtins__": {}}, allowed_names)

    except Exception as e:
        result = f"Error: {str(e)}"

    print(f"Received from {addr}: {expr}")
    server_socket.sendto(str(result).encode(), addr)
