import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CreateStickers {
  
  public void create(InputStream inputStream, String  outputFile) throws Exception {
    // leitura da imagem
    /* InputStream inputStream = new FileInputStream(new File("IMDbAlura/assets/movie.jpg")); */
    /* InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream(); */
    BufferedImage originalImage = ImageIO.read(inputStream);

    // criar nova imagem em memoria com transparência e com tamanho novo
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();

    int newHeight = height + 170;
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // copiar imagem original para novo imagem (memoria)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null); /* OBS: Imagem antiga */

    //configurar fonte
    var font = new Font(Font.SANS_SERIF, Font.BOLD, 130);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    
    // escrever uma frase na nova imagem
    graphics.drawString("** Ótimo Filme **", 195, newHeight - 100);

    // escrever a nova imagem em um arquivo
    ImageIO.write(newImage, "png", new File(outputFile));
  }
}