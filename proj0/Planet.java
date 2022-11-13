public class Planet
{
    public double xxPos,yyPos,xxVel,yyVel,mass;
    public String imgFileName;
    final private static  double G = 6.67e-11;
    public Planet(double xP,double yP,double xV,
    double yV,double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;       
    }
    public Planet(Planet b)
    {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass  = b.mass;
        imgFileName = b.imgFileName;   
    }
    public double calcDistance(Planet another_body)
    {   
        double square_add;
        square_add = Math.pow(this.xxPos-another_body.xxPos,2)
        +Math.pow(this.yyPos-another_body.yyPos,2);
        return Math.sqrt(square_add);
    }
    public double calcForceExertedBy(Planet exert_planet)
    {
        double distance = this.calcDistance(exert_planet);
        double result = G*this.mass*exert_planet.mass/Math.pow(distance,2);
        return result;
    }
    public double calcForceExertedByX(Planet exext_planet)
    {
        double result = this.calcForceExertedBy(exext_planet) *
        (exext_planet.xxPos - this.xxPos) / this.calcDistance(exext_planet);
        return result;
    }
    public double calcForceExertedByY(Planet exext_planet)
    {
        double result = this.calcForceExertedBy(exext_planet) *
        (exext_planet.yyPos - this.yyPos) / this.calcDistance(exext_planet);
        return result;
    }
    public double calcNetForceExertedByX(Planet[] Planets)
    {
        int i = 0;
        double result_x = 0;
        while (i<Planets.length)
        {   
            if (this.equals(Planets[i]) != true )
            {
               result_x = result_x + this.calcForceExertedByX(Planets[i]); 
             
            }
            i = i + 1;
        }
    return result_x;
    }
    public double calcNetForceExertedByY(Planet[] Planets)
    {
        int i = 0;
        double result_y = 0;
        while (i<Planets.length)
        {   
            if (this.equals(Planets[i]) != true )
            {
               result_y = result_y +this.calcForceExertedByY(Planets[i]);
            }
            i = i + 1;
        }
    return result_y;
    }
    public void update(double dt,double fX,double fY)
    {
       double aX = fX / this.mass;
       double aY = fY / this.mass;
       this.xxVel = this.xxVel + aX * dt;
       this.yyVel = this.yyVel + aY * dt;
       this.xxPos = this.xxPos + this.xxVel * dt;
       this.yyPos = this.yyPos + this.yyVel * dt; 
    }
    public void draw()
    {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);        
    }
}
