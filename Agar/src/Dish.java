import java.util.ArrayList;

public class Dish
{

  public static void main(String[] args)
  {
    int width = 2000;
    int height = 200;

    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(new jackCellv1());
    cells.get(0).xVel = 1;
    cells.get(0).type = "Cell";
    
    while (true)
    {
      Agar(cells, width, height);
    }
  }

  public static void Agar(ArrayList<Cell> cells, int width, int height)
  {
    for (int i = 0; i < cells.size(); i++)
    {
      // trace("Loop");

      if (cells.get(i).type.equals("Cell"))
      {
        cells.get(i).main();
        trace(cells.get(i).div);
      }
      wallDetect(cells.get(i), width, height);
      cells = hitDetect(cells);

    }

  }

  public static ArrayList<Cell> hitDetect(ArrayList<Cell> cells)
  {
    if (cells.size() > 1)
    {

      for (int i = 0; i < cells.size(); i++)
      {
        if (cells.get(i).type.equals("Cell"))
        {
          for (int j = i + 1; j < cells.size(); j++)
          {
            int xDis = cells.get(i).xCord - cells.get(j).xCord;
            int yDis = cells.get(i).yCord - cells.get(j).yCord;
            double distance = Math.abs((xDis ^ 2 + yDis ^ 2) ^ (1 / 2));
           
            if (distance < cells.get(i).mass / 2 + cells.get(j).mass / 2)
            {
             
              cells = eat(cells, i, j);

            }
          }
        }
      }
    }
    return cells;
  }

  public static ArrayList<Cell> eat(ArrayList<Cell> cells, int i, int j)
  {

    if (cells.get(i).mass / cells.get(j).mass > 2)
    {
      cells.get(i).eat(cells.get(j));
      cells.remove(j);
    }
    else if (cells.get(j).mass / cells.get(i).mass > 2)
    {
      cells.get(j).eat(cells.get(i));
      cells.remove(i);
    }

    return cells;
  }

  public static void wallDetect(Cell cCell, int width, int height)
  {
    if (cCell.type.equals("Cell"))
    {
      if (cCell.xCord + cCell.mass / 2 > width)
      {
        cCell.xCord = width - cCell.mass / 2;
        cCell.xVel = 0;
        cCell.xA = 0;
      }
      if (cCell.yCord + cCell.mass / 2 > height)
      {
        cCell.yCord = height - cCell.mass / 2;
        cCell.yVel = 0;
        cCell.yA = 0;
      }
      if (cCell.xCord + cCell.mass / 2 < 0)
      {
        cCell.xCord = 0 + cCell.mass / 2;
        cCell.xVel = 0;
        cCell.xA = 0;
      }
      if (cCell.yCord + cCell.mass / 2 < 0)
      {
        cCell.yCord = 0 + cCell.mass / 2;
        cCell.yVel = 0;
        cCell.yA = 0;
      }
    }
  }

  public static void trace(String a)
  {
    System.out.println(a);
  }

  public static void trace(int a)
  {
    System.out.println(a);
  }

  public static void trace(double a)
  {
    System.out.println(a);
  }
  public static void trace(boolean a)
  {
    System.out.println(a);
  }
}
