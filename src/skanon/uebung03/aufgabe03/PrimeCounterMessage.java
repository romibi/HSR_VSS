package skanon.uebung03.aufgabe03;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PrimeCounterMessage implements Serializable {
    public static final long serialVersionUID = 1L;
    public final long start;
    public final long end;

    public PrimeCounterMessage(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return String.format("Prime numbers from %d to %d", start, end);
    }

    public byte[] toBytes() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
            oos.flush();
            return baos.toByteArray();
        }
    }

    public static PrimeCounterMessage fromBytes(byte[] body) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body))) {
            Object msg = ois.readObject();
            if (!(msg instanceof PrimeCounterMessage)) {
                throw new IllegalArgumentException("Invalid message type");
            }
            return (PrimeCounterMessage) msg;
        }
    }
}