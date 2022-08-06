import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class CreateStickers {
  
  public void create() throws Exception {
    // leitura da imagem
    BufferedImage originalImage = ImageIO.read(new File("IMDbAlura/assets/movie.jpg"));

    // criar nova imagem em memoria com transparência e com tamanho novo
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();

    int newHeight = height + 170;
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // copiar imagem original para novo imagem (memoria)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null); /* OBS: Imagem antiga */

    // escrever uma frase na nova imagem

    // escrever a nova imagem em um arquivo
    ImageIO.write(newImage, "png", new File("IMDbAlura/saida/figurinha.png"));
  }

  public static void main(String[] args) throws Exception {
    var geradora = new CreateStickers();
    geradora.create();
  }
}
