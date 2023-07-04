import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class HoughLinesRun {
	
    public void run(String[] args) {
        
        Mat dst = new Mat(), cdst = new Mat(), cdstP;
        String default_file = "C:\\Users\\fede\\Documents\\NetBeansProjects\\TP4\\src\\img\\figuras.png";
        Mat imgOriginal = Imgcodecs.imread(default_file);    
        HighGui.imshow("Imagen original", imgOriginal);
        Mat src = Imgcodecs.imread(default_file, Imgcodecs.IMREAD_GRAYSCALE);
        //se verifica que existe la imagen dentro del directorio indiciado, sino se muestra por pantalla un msj y se finaliza el proceso
        if( src.empty() ) {
            System.out.println("Error al abrir la imagen");
            System.out.println("El directorio donde se esta buscando es: "
                    + default_file);
            System.exit(-1);
        }
        //Se utiliza la técnica Canny para detectar los bordes en la imagen
        Imgproc.Canny(src, dst, 50, 200, 3, false);
        //Se convierte la imagen a escala de grises
        Imgproc.cvtColor(dst, cdst, Imgproc.COLOR_GRAY2BGR);
        cdstP = cdst.clone();
        
        Mat linesP = new Mat(); // Matriz linesP
        //aplicamos la transformada de Hough con la ayuda de la libreria openCV
        Imgproc.HoughLinesP(dst, linesP, 1, Math.PI/180, 50, 50, 10); // runs the actual detection
        //convertimos los puntos de las lineas detectadas en rojo
        for (int x = 0; x < linesP.rows(); x++) {
            double[] conv = linesP.get(x, 0);
            //dibuja una linea roja desde los puntos seleccionados en una nueva imagen
            Imgproc.line(cdstP, new Point(conv[0], conv[1]), new Point(conv[2], conv[3]), new Scalar(0, 0, 255), 2, Imgproc.LINE_AA,0);
        }
        HighGui.imshow("Imagen original en grises", src); //muestra la imagen con fondo blanco y figura en grises
        HighGui.imshow("Imagen con fondo negro y bordes en blanco", cdst);
        HighGui.imshow("Imagen con lineas detectadas (en rojo) - Aplicada la Transformada de Hough", cdstP);
        HighGui.waitKey();
        System.exit(0);
        
    }
    
}
public class HoughLines {
    public static void main(String[] args) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new HoughLinesRun().run(args);
    }
}