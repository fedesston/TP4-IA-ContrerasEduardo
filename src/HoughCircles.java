import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class HoughCircles {
    public static void main(String[] args) {
        // Se carga la libreria OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Cargamos la imagen del motor
        Mat image = Imgcodecs.imread("C:\\Users\\fede\\Documents\\NetBeansProjects\\TP4\\src\\img\\motorPrueba.jpg");

        // Preprocesamiento (para que sea más facil detectar el aro)
        Imgproc.GaussianBlur(image, image, new Size(5, 5), 0);
        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);

        // Detección de circunferencias
        Mat circles = new Mat();
        Imgproc.HoughCircles(image, circles, Imgproc.CV_HOUGH_GRADIENT, 1, image.rows() / 8, 100, 30, 35, 38);

        // Procesar los resultados
        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point center = new Point(circle[0], circle[1]);
            int radius = (int) Math.round(circle[2]);

            // Dibujar la circunferencia en la imagen original
            Imgproc.circle(image, center, radius, new Scalar(255, 0, 0), 2);
            //detectamos el punto medio de la circunferencia y lo pintamos
            Point midPoint = new Point(center.x, center.y);
            Imgproc.circle(image, midPoint, 3, new Scalar(255, 0, 0), -1);
        }

        // Guardar la imagen con la circunferencia detectada
        Imgcodecs.imwrite("C:\\Users\\fede\\Documents\\NetBeansProjects\\TP4\\src\\img\\MotorConCircunferenciaDetectada.jpg", image);
    }
}