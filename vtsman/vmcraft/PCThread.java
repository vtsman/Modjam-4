package vtsman.vmcraft;

import java.io.IOException;
import java.util.logging.Level;

import org.jpc.emulator.PC;
import org.jpc.j2se.VirtualClock;

public class PCThread implements Runnable {
	PC pc;

	public PCThread(PC p) {
		pc = p;
	}

	@Override
	public void run() {
		pc.start();
		try {
			while (true) {
				pc.execute();
			}
		} finally {
			pc.stop();
			pc.getProcessor().printState();
		}
	}

}
