public class NBody
{
    public static double readRadius(String file_path)
    {
        In in = new In(file_path);
        int planets_num = in.readInt();
        double Radius = in.readDouble();
        return Radius;  
    }
    public static Planet[] readPlanets(String file_path)
    {
        In in = new In(file_path);
        int i = 0;  
        int planets_num = in.readInt();
        double Radius = in.readDouble();
        Planet[] planets = new Planet[planets_num];
        while (i != planets_num)
        {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in .readDouble();
            String img = in.readString();
            Planet cur_planet = new Planet(xP,yP,xV,yV,m,img);
            planets[i] = cur_planet;    
            i++;    
        }
        return planets;
    }
    public static void main(String[] args) 
    {
        double T = Double.parseDouble(args[0]);  
        double dt = Double.parseDouble(args[1]);     
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename); 
        StdDraw.setScale(-radius,radius); 
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i=0;i<planets.length;i++)
        {
            planets[i].draw();     
        } 
        double time = 0;
        StdDraw.enableDoubleBuffering();
        while (time != T)
        {
           double[] xForces = new double[planets.length];
           double[] yForces = new double[planets.length];
           int i = 0;
           while (i != planets.length)
           {
            xForces[i] = planets[i].calcNetForceExertedByX(planets);
            yForces[i] = planets[i].calcNetForceExertedByY(planets);
            i++;
           } 
           int index = 0;
           while(index < planets.length)
           {
            planets[index].update(dt,xForces[index] ,yForces[index]);
            index++;
           } 
           StdDraw.picture(0, 0, "images/starfield.jpg");
           int index2 = 0;
           while (index2 != planets.length)
           {
            planets[index2].draw();
            index2 ++;
           }
           StdDraw.show();
           StdDraw.pause(10); 
           time += dt;  
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
                  StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
            }
   }
           
}