from py4j.java_gateway import JavaGateway, CallbackServerParameters


class PythonListener(object):

    def __init__(self, gateway):
        self.gateway = gateway

    def notify(self, obj):
        print(obj)

        return "A Return Value"

    class Java:
        implements = ["org.py4j.smallbench.BenchListener"]


def main():
    gateway = JavaGateway(
        callback_server_parameters=CallbackServerParameters())
    listener = PythonListener(gateway)
    gateway.entry_point.registerBenchListener(listener)
    gateway.entry_point.startProducers(5)
    # gateway.shutdown()


if __name__ == "__main__":
    main()
