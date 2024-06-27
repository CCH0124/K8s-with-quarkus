# -*- encoding: utf-8 -*-
import signal
from time import sleep

# SIGINT is a signal used to request a graceful termination of a process, typically initiated by the user. 
# SIGTERM is a signal used to request a process to terminate gracefully, which can be sent by other processes or the system itself.
def by_user(signum, frame):
    if signum == signal.SIGINT:
        print("Sending SIGINT to process")

    print()
    print('Good Bye')
    print()

    exit(0)

def by_system(signum, frame):    
    if signum == signal.SIGTERM:
        print("Sending SIGTERM to process")

    print()
    print('Graceful Good Bye')
    print()

    exit(0)


if __name__ == '__main__':
    # Register handler
    signal.signal(signal.SIGINT, by_user)
    signal.signal(signal.SIGTERM, by_system)
    while True:
        print('waiting...')
        sleep(500)
