import java.util.ArrayList;
import java.util.Random;

public class Cell implements MainInterface
{
  public int xCord;
  public int yCord;
  public int xVel;
  public int yVel;
  public int xA;
  public int yA;
  public int mass;
  public ArrayList<Cell> subCells = new ArrayList<Cell>();
  public boolean div;
  public String type;
  public ArrayList<Cell> sense;

  // Constructors
  public Cell()
  {
    xVel = 0;
    yVel = 0;
    xA = 0;
    yA = 0;
    mass = 10;
    xCord = 0;
    yCord = 0;
    type = "Food";
  }

  public Cell(int xC, int yC, int xV, int yV, int xAc, int yAc, int ma,
      String a, ArrayList<Cell> b)
  {
    xCord = xC;
    yCord = yC;
    xVel = xV;
    yVel = yV;
    xA = xAc;
    yA = yAc;
    mass = ma;
    type = a;
    sense = b;
  }

  public Cell(int width, int height, String a)
  {
    xVel = 0;
    yVel = 0;
    xA = 0;
    yA = 0;
    mass = 10;
    type = a;
    Random gen = new Random(width);
    xCord = gen.nextInt();
    gen = new Random(height);
    yCord = gen.nextInt();

  }

  // Methods

  public void update()
  {
    xVel += xA;
    yVel += yA;
    xCord += xVel;
    yCord += yVel;
    if (div)
    {
      for (Cell a : subCells)
      {
        a.xVel = this.xVel;
        a.yVel = this.yVel;
        a.xCord += a.xVel;
        a.yCord += a.yVel;
      }
    }
  }

  public void eat(Cell cell)
  {

    if (div)
    {
      for (Cell a : subCells)
      {
        a.mass += cell.mass / (subCells.size() + 1);
      }
      this.mass += cell.mass / (subCells.size() + 1);
    }
    else
    {
      mass += cell.mass;
    }
  }

  public void divide()
  {
    if (mass > 60)
    {
      if (div)
      {
        for (Cell a : subCells)
        {
          if (mass > 60)
          {
            if (xVel != 0 && yVel != 0)
            {
              subCells.add(new Cell((xVel / xVel) * a.mass, (yVel / yVel)
                  * a.mass, xVel, yVel, 0, 0, a.mass / 2, "Cell", null));
              a.mass = a.mass / 2;
              div = true;
            }
            else
            {
              subCells.add(new Cell((1) * a.mass, (1) * a.mass, xVel, yVel, 0,
                  0, a.mass / 2, "Cell", null));
              a.mass = a.mass / 2;
              div = true;
            }
          }
        }

      }
      else
      {
        if (xVel != 0 && yVel != 0)
        {
          subCells.add(new Cell((xVel / xVel) * mass, (yVel / yVel) * mass,
              xVel, yVel, 0, 0, mass / 2, "Cell", null));
          mass = mass / 2;
          div = true;

        }
        else
        {
          subCells.add(new Cell((1) * mass, (1) * mass, xVel, yVel, 0, 0,
              mass / 2, "Cell", null));
          mass = mass / 2;
          div = true;
        }
      }
    }

  }

  public void main()
  {

  }

}
