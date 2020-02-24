from threading import Thread
import traceback
import sqlalchemy
import time
import socket
import sys
NUM_CLIENT = 2
LOCALHOST = '127.0.0.1'
DEFAULTPORT = 9000

def listen_socket():
    """Create a socket and bind the local address with default port"""
    sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    server_addr = (LOCALHOST, DEFAULTPORT)
    sock.bind(server_addr)

    'listen to any connection and accept that client'
    try:
        sock.listen(NUM_CLIENT)

        while True:
            conn, addr = sock.accept()
            ip,port =str(addr[0]),str(addr[1])
            print("IP:",ip)
            print("PORT:",port)
            try:
                Thread(target=client_thread, args=(conn,addr,ip,port)).start()
            except:
                print("Thread initializing failed..")
                traceback.print_exc()

    except:
        print("Error connecting")

def client_thread(conn,addr,ip,port):
    print("Connected to ", addr)
    while(True):
        client_input = conn.recv(5120).decode()
        if not client_input:
            break
        if client_input == 'quit\r\n':
            conn.close()
            print("Connection to " + ip + ":" + port + " closed")
            break
        else:
            print(client_input)
            data = input('->')
            data = 'Server Said: \r\n' + data + '\r\n'
            conn.send(data.encode())
            #conn.send(client_input.encode())

listen_socket()