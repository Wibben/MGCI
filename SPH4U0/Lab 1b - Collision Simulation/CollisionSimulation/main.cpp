/** SPH4U0
  * Matthew Chiang, Bing Li, Reza Mohammadi, Frederick Ngo
  * Mr. van Bemmel
  * Collision Simulation
  */
#include <iostream>
#include <cmath>
#include <fstream>
#include <string>

#define PI (atan(1)*4)

using namespace std;

/**
 * Vector class
 */

// Vector class to encompass displacement, velocity, and acceleration
class Vector2D
{
    double magnitude,theta; // Magnitude and direction
    double x,y; // Components
  public:
    // Default Constructor
    Vector2D() { magnitude = theta = x = y = 0; }
    // Setters
    void setPolar(double magnitude, double theta);
    void setCartesian(double x, double y);
    // Getters
    double getMagnitude() { return magnitude; }
    double getTheta() { return theta; }
    double getx() { return x; }
    double gety() { return y; }
};

void Vector2D::setPolar(double magnitude, double theta)
{
    this->magnitude = magnitude;
    this->theta = theta;
    // Set cartesian coordinates from polar
    x = magnitude*cos(theta*PI/180);
    y = magnitude*sin(theta*PI/180);
}

void Vector2D::setCartesian(double x, double y)
{
    this->x = x;
    this->y = y;
    // Set polar coordinates from cartesian
    // Change theta based on x and y's characteristics
    if(x>0 && y>=0) theta = atan(y/x)*180/PI;
    else if(x==0 && y>=0) theta = 90;
    else if(x==0 && y<0) theta = 270;
    else if(x<0) theta = 180+atan(y/x)*180/PI;
    else theta = 360+atan(y/x)*180/PI;

    theta = abs(theta)-360*((int)(theta)/360);

    magnitude = (cos(theta*PI/180)==0 ? y/sin(theta*PI/180):x/cos(theta*PI/180)); // Extra layer of checking to make sure not dividing by 0
}

/**
 * Ball class
 */

class Ball
{
    double k,mass,radius,maximum;
  public:
    Vector2D s,v,a;
    // Default constructor
    Ball() :s(),v(),a() {k=1; mass=radius=maximum=0;}
    // Increment position using time
    void increment(double t);
    // Check if collision has occurred
    bool hit(Ball b);
    // Function for the spring force of the ball based on compression x
    Vector2D spring(Vector2D x);
    // Setters
    void setRadius(double r) { radius = r; }
    void setMass(double m) { mass = m; }
    void setMax(double m) { maximum = m; }
    // Getters
    double getRadius() { return radius; }
    double getMass() { return mass; }
    double getMax() { return maximum; }
};

// Update position and velocity vectors
// Must update position first as it uses the initial velocity
void Ball::increment(double t)
{
    s.setCartesian(s.getx()+t*v.getx()+0.5*t*t*a.getx(),s.gety()+t*v.gety()+0.5*t*t*a.gety()); // S = So + Vot + 0.5at^2
    v.setCartesian(v.getx()+t*a.getx(),v.gety()+t*a.gety()); // V = Vo + at
}

// Check if the Balls have collided by checking if the
// distance of their centers is less than the sum of their radii
bool Ball::hit(Ball b)
{
    double dx,dy,dist;
    // Get the vertical and horizontal distances
    dx = abs(s.getx()-b.s.getx());
    dy = abs(s.gety()-b.s.gety());
    // Pythagorean theorem for actual distance
    dist = sqrt(dx*dx+dy*dy);

    return dist<(radius+b.getRadius());
}

// Find the spring froce from the ball based on its compression
Vector2D Ball::spring(Vector2D x)
{
    Vector2D F; // Return some function found in regression
    /**
     * F = -a*e^(b*x)
     * After regression:
     * F = -e^(1.188*x)
     */

    F.setCartesian(-exp(118.8*x.getx()),-exp(118.8*x.gety()));

    return F;
}

/**
 * Main Program
 */

void getinput(Ball * a, Ball * b, double * duration, int index)
{
    // Get file input
    char filename[] = "input0.txt";
    filename[5] = '0'+index;
    cout << "Reading " << filename << "..." << endl;
    ifstream in(filename);
    double x,y,m,r,maximum;
    string discard;

    // Time
    getline(in,discard); // "Simulation duration: (s)"
    in >> *duration; in.ignore();

    // Object A
    getline(in,discard); // Empty line
    getline(in,discard); // "Object A:"
    getline(in,discard); // "Initial Position: (x y)"
    in >> x >> y; in.ignore();
    a -> s.setCartesian(x,y); // Initial position

    getline(in,discard); // "Initial Velocity: (x y)"
    in >> x >> y; in.ignore();
    a -> v.setCartesian(x,y); // Initial velocity

    getline(in,discard); // "Mass: (kg)"
    in >> m; in.ignore();
    getline(in,discard); // "Radius: (m)"
    in >> r; in.ignore();
    getline(in,discard); // "Maximum Compression: (m)"
    in >> maximum; in.ignore();
    a -> setMass(m); // Mass
    a -> setRadius(r); // Radius
    a -> setMax(maximum); // Max compression

    // Object B
    getline(in,discard); // Empty line
    getline(in,discard); // "Object B:"
    getline(in,discard); // "Initial Position: (x y)"
    in >> x >> y; in.ignore();
    b -> s.setCartesian(x,y); // Initial position

    getline(in,discard); // "Initial Velocity: (x y)"
    in >> x >> y; in.ignore();
    b -> v.setCartesian(x,y); // Initial velocity
// Get direction of the normal
    getline(in,discard); // "Mass: (kg)"
    in >> m; in.ignore();
    getline(in,discard); // "Radius: (m)"
    in >> r; in.ignore();
    getline(in,discard); // "Maximum Compression: (m)"
    in >> maximum; in.ignore();
    b -> setMass(m); // Mass
    b -> setRadius(r); // Radius
    b -> setMax(maximum); // Max compression

    in.close(); // Close the file
}

int main()
{
    int files;

    cout << "How many input files do you have? ";
    cin >> files;

    for(int index=0; index<files; index++) {
        Ball a,b;
        double duration;
        char filename[] = "out0.csv";
        filename[3] = '0'+index;
        ofstream out(filename);

        // Get user input
        getinput(&a,&b,&duration,index);

        // Column headings and initial conditions
        out << "time,a.s.x,a.s.y,a.v.x,a.v.y,a.a.x,a.a.y,b.s.x,b.s.y,b.v.x,b.v.y,b.a.x,b.a.y" << endl;
        out << "0," << a.s.getx() << "," << a.s.gety() << "," << a.v.getx() << "," << a.v.gety() << ","<< a.a.getx() << "," << a.a.gety() << ","
            << b.s.getx() << "," << b.s.gety() << "," << b.v.getx() << "," << b.v.gety() << ","<< b.a.getx() << "," << b.a.gety() << endl;

        for(double tick=0.0001,t=tick; t<duration; t+=tick) { // Timer to achieve simulation, tick is how often time is incremented
            // Increment positions given time
            a.increment(tick);
            b.increment(tick);

            // Set acceleration as 0, if the balls are not touching it should be 0
            // otherwise the if statmeent should take care of it
            a.a.setCartesian(0,0);
            b.a.setCartesian(0,0);

            if(a.hit(b)) {
                // Find compression depth
                double dx,dy,dist;
                // Get the vertical and horizontal distances
                dx = a.s.getx()-b.s.getx();
                dy = a.s.gety()-b.s.gety();
                // Pythagorean theorem for actual distance
                dist = sqrt(dx*dx+dy*dy);

                double depth = (a.getRadius()+b.getRadius() - dist)/2; // Depth of each ball is the same

                // Calculate force based on depth
                Vector2D adir,bdir,adepth,bdepth,Fa,Fb;

                // Get direction of the normal
                adir.setCartesian(b.s.getx()-a.s.getx(),b.s.gety()-a.s.gety()); // Get direction of the normal
                bdir.setCartesian(-adir.getx(),-adir.gety());

                // Get a vector for the compression
                adepth.setPolar(depth,adir.getTheta());
                bdepth.setPolar(depth,bdir.getTheta());

                // Get the force vectors from the compression vectors
                Fa = a.spring(adepth);
                Fb = b.spring(bdepth);

                // Calculate new accelerations based on depth
                // Set the cartesian of the acceleration as a function of the sum of the forces
                a.a.setCartesian((Fa.getx()-Fb.getx())/a.getMass(),(Fa.gety()-Fb.gety())/a.getMass());
                b.a.setCartesian((-Fa.getx()+Fb.getx())/b.getMass(),(-Fa.gety()+Fb.gety())/b.getMass());

                // Maximum compression, system becomes completely inelastic and balls travel
                if(dist<(a.getMax()/2 + b.getMax()/2)) {
                    cout << "INELASTIC AT: " << t << endl;
                    a.v.setCartesian((a.getMass()*a.v.getx()+b.getMass()*b.v.getx())/(a.getMass()+b.getMass()),
                                     (a.getMass()*a.v.gety()+b.getMass()*b.v.gety())/(a.getMass()+b.getMass()));
                    b.v.setCartesian((a.getMass()*a.v.getx()+b.getMass()*b.v.getx())/(a.getMass()+b.getMass()),
                                     (a.getMass()*a.v.gety()+b.getMass()*b.v.gety())/(a.getMass()+b.getMass()));
                }
            }

            // Output position
            out << t << "," << a.s.getx() << "," << a.s.gety() << "," << a.v.getx() << "," << a.v.gety() << ","<< a.a.getx() << "," << a.a.gety() << ","
                << b.s.getx() << "," << b.s.gety() << "," << b.v.getx() << "," << b.v.gety() << ","<< b.a.getx() << "," << b.a.gety() << endl;
        }

        out.close();
        cout << "Completed " << filename << endl;
    }

    return 0;
}
