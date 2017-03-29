package net.liuxuan.utils.algorithm.fourier.kind2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;

class fft {
	final static double PI = 3.1415926535897931;
	final static double HALFPI = PI / 2;
	static Complex w(int n, int power) {

		double u = 2 * PI * power / (double) n;

		double threshold = 0.0000000001;
		if (Math.abs(u - 0) < threshold)
			return new Complex(1, 0);
		else if (Math.abs(u - HALFPI) < threshold)
			return new Complex(0, 1);
		else if (Math.abs(u - PI) < threshold)
			return new Complex(-1, 0);
		else if (Math.abs(u - 3 * HALFPI) < threshold)
			return new Complex(0, -1);
		else if (Math.abs(u - 4 * HALFPI) < threshold)
			return new Complex(1, 0);
		else
			return new Complex(Math.cos(u), Math.sin(u));
	}

	public static void FFT(int n, int[] a, Complex w, Complex[] V) {

		if (n == 1) {
			V[0] = new Complex(a[0], 0);
		} else {
			int[] b;
			Complex[] U = new Complex[n / 2];
			Complex[] W = new Complex[n / 2];
			b = new int[n / 2];
			for (int i = 0; i < n / 2; i++)
				b[i] = a[i * 2];
			FFT(n / 2, b, w.times(w), U);

			for (int i = 0; i < n / 2; i++)
				b[i] = a[i * 2 + 1];
			FFT(n / 2, b, w.times(w), W);

			for (int j = 0; j < n / 2; j++) {
				V[j] = U[j].plus(w.power(j).times(W[j]));
				V[j + n / 2] = U[j].minus(w.power(j).times(W[j]));

			}
		}
	}

	public static void IFFT(int n, Complex[] a, Complex w, Complex[] V) {

		if (n == 1) {
			a[0] = V[0];
		} else {
			Complex[] b;
			Complex[] B = new Complex[n / 2];
			Complex[] C = new Complex[n / 2];
			b = new Complex[n / 2];
			for (int i = 0; i < n / 2; i++)
				b[i] = V[i * 2];
			IFFT(n / 2, B, w.times(w), b);

			for (int i = 0; i < n / 2; i++)
				b[i] = V[i * 2 + 1];
			IFFT(n / 2, C, w.times(w), b);

			for (int j = 0; j < n / 2; j++) {
				a[j] = B[j].plus(w.power(j).times(C[j]));
				a[j + n / 2] = B[j].minus(w.power(j).times(C[j]));
			}
		}
	}
	public static void main(String argv[]) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("data5.txt"));
		String s;
		s = in.readLine();
		int n = Integer.parseInt(s) + 1;
		String s1 = "";
		int i = 0;
		int j = 0;
		int[] a = new int[2 * n];
		int[] b = new int[2 * n];
		for (j = 0; j < n; j++) {
			a[j] = 0;
			b[j] = 0;
		}
		s = in.readLine();
		char[] chars = new char[s.length()];
		s.getChars(0, s.length(), chars, 0);
		while (i < chars.length) {
			for (; i < chars.length && chars[i] != ','; i++) {
				s1 += chars[i];
			}
			a[j++] = Integer.parseInt(s1);
			s1 = "";
			i++;
		}

		s = in.readLine();
		s.getChars(0, s.length(), chars, 0);
		i = 0;
		j = n;
		while (i < chars.length) {
			for (; i < chars.length && chars[i] != ','; i++) {
				s1 += chars[i];
			}
			b[j++] = Integer.parseInt(s1);
			s1 = "";
			i++;
		}

		Complex[] A = new Complex[2 * n];
		Complex[] B = new Complex[2 * n];
		Complex w = w(2 * n, 1);
		FFT(2 * n, a, w, A);
		FFT(2 * n, b, w, B);
		Complex[] C = new Complex[2 * n];
		
		for (i = 0; i < 2 * n; i++)
			C[i] = A[i].times(B[i]);
		Complex[] Result = new Complex[2 * n];
		w = w(2 * n, 2 * n - 1);
		IFFT(2 * n, Result, w, C);

		int[] result = new int[2 * n];
		PrintWriter out = new PrintWriter("result5.txt");
		for (i = 0; i < 2 * n-1; i++) {
			result[i] = (int) Math.round(Result[i].real()/2/n);//��ӽ�����
//			System.out.println(result[i]);
			out.print(result[i]+",");
		}
//		System.out.println(result[i]);
		out.println(result[i]);
		in.close();
		out.close();

	}
}
