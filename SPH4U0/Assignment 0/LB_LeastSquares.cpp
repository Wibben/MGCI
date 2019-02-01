/** SPH4U0
  * Bing Li
  * Mr. van Bemmel
  * Assignment 0
  * 4.3.4.1 - Least Squares
  */
#include <iostream>
#include <vector>
#include <cmath>
#include <iomanip>

using namespace std;

// Creating a matrix class for clarity and reusability
class Matrix
{
    int row,col;
    vector< vector<double> > mat;
  public:
    // Initializers
    void init(int,int); // Creates a vector of size row x col filled with 1s
    void init(int,int,double); // Creates a vector of size row x col filled with a chosen value

    // Main methods
    Matrix transpose(); // Transpose of the matrix
    double determinant(); // Getting the determinant
    Matrix inverse(); // Inverse of a matrix

    // Getters and setters
    void display(); // Display the matrix
    double getval(int,int); // Get value of a specific cell
    void setval(int,int,double); // Set value of a specific cell

    // Overloaded operators
    Matrix operator * (const Matrix&); // Defining matrix multiplication
    Matrix operator * (const double&); // Defining matrix multiplication by a number
};

// Creates a vector of size row x col filled with 1s
void Matrix::init(int row, int col)
{
    init(row,col,1); // Simply calls the other, more specific method
}

// Creates a vector of size row x col filled with 1s
void Matrix::init(int row, int col, double val)
{
    this -> row = row;
    this -> col = col;
    for(int i=0; i<row; i++) {
        vector<double> tempMat;
        mat.push_back(tempMat);
        for(int j=0; j<col; j++) {
            mat[i].push_back(val);
        }
    }
}

// Get value of a specific cell
double Matrix::getval(int row, int col)
{
    return mat[row][col];
}

// Set value of a specific cell
void Matrix::setval(int row, int col, double val)
{
    mat[row][col] = val;
}

// Transpose of a matrix
// The row and columns switch
Matrix Matrix::transpose()
{
    Matrix matT; // Transpose matrix
    matT.init(col, row);

    // Loop through original matrix and find new location
    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            matT.setval(j,i,mat[i][j]);
        }
    }

    return matT;
}

/** Get the detemrminant, recursive process
  * Determinants can be found by multiplying a number in the top row by the
  * determinate of the matrix created by the other rows in the unused columns,
  * they also have alternating signs starting from + - + - ...
  */
double Matrix::determinant()
{
    if(row==1) return mat[0][0]; // A 1x1 matrix's determinant is itself
    else {
        double ans = 0; // For storing the answer

        for(int i=0; i<col; i++) { // Looping through the first line
            // Temporary matrix for storing the smaller matrices
            Matrix temp;
            temp.init(row-1,col-1);
            for(int j=1; j<row; j++) { // Loop from second to last row
                for(int k=0,cnt=0; k<col; k++) { // Loop every column
                    if(k!=i) { // Exclude the column of the coefficient
                        temp.setval(j-1,cnt,mat[j][k]);
                        cnt++; // Increment counter only if value has been assigned
                    }
                }
            }

            ans += pow(-1,i)*mat[0][i]*temp.determinant(); // Recursive call
        }

        return ans;
    }
}

/** Get the inverse of the matrix
  * Using minors, cofactors, and adjugate:
  * 1. Get the minors matrix of the original
  * 2. Find its cofactor matrix
  * 3. Transpose the resulting matrix
  * 4. Multiply by 1/determinant
  */
Matrix Matrix::inverse()
{
    Matrix temp;
    temp.init(row,col);

    // Minors: Each cell is the determinant of the matrix
    // created by omitting the current row and column
    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            // Create a matrix for finding the determinant
            Matrix det;
            det.init(row-1,col-1);

            // Assigning values to the det matrix
            for(int k=0,cntr=0; k<row; k++) { // cntr keeps track of current row of det matrix
                if(k!=i) { // Omitting the current row
                    for(int l=0,cntc=0; l<col; l++) { // cntc keeps track of current column of det matrix
                        if(l!=j) { // Omitting the current column
                            det.setval(cntr,cntc,mat[k][l]);
                            cntc++; // Increment column afterwards
                        }
                    }
                    cntr++; // Increment row after each column is completed
                }
            }

            temp.setval(i,j,det.determinant()); // Set the value of the current cell with its minor
        }
    }

    // Cofactors: Alternatly multiply by +1 and -1,
    // starting with +1 on odd rows and -1 on even rows
    for(int i=0; i<row; i++) { // Go through each row
        int multiplier = pow(-1,i); // Figure out initial multiplir based on row
        for(int j=0; j<col; j++,multiplier*=-1) { // Go through each column, multiplier alternates each loop
            temp.setval(i,j,multiplier*temp.getval(i,j));
        }
    }

    // Adjugate
    temp = temp.transpose();

    // Mutiply
    temp = temp*(1.0/determinant());

    return temp;
}

// Display the matrix
// Loops through the entire matrix by row and col and outputs each cell with formatting
void Matrix::display()
{
    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            cout << setw(11) << fixed << setprecision(3); // Field width of 11, and always shows 3 decimal places
            if(abs(mat[i][j])>=100000 || abs(mat[i][j])<0.001) cout << scientific; // Use scientific notation if number is 6 digits or less than 3 decimals
            cout << mat[i][j];
        }
        cout << endl;
    }
}

// Overloading the * operator for matrix multiplication
Matrix Matrix::operator*(const Matrix& param)
{
    Matrix temp;
    temp.init(row,param.col);

    // Matrix multiplication is done by multiplying the current row of the first matrix
    // by the cirrent column of the second matrix
    for(int i=0; i<row; i++) {
        for(int j=0; j<param.col; j++) {
            double ans = 0;
            for(int k=0; k<col; k++) {
                ans += mat[i][k]*param.mat[k][j];
            }
            temp.setval(i,j,ans);
        }
    }

    return temp;
}

// Overloading the * operator for multiplication by a number
Matrix Matrix::operator*(const double& param)
{
    Matrix temp;
    temp.init(row,col);

    // Matrix multiplication by a number is done by multiplying
    // everything int eh matrix by that number
    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            temp.setval(i,j,mat[i][j]*param);
        }
    }

    return temp;
}

int main()
{
    // Setting up variables
    Matrix xvals,yvals,V,Vt,VtV,iVtV,c;
    int degree,datapoints;
    double val;

    // Getting input
    cout << "Please enter the degree of the polynomial you with to fit: ";
    cin >> degree;
    cout << "Please enter the number of data points you wish to enter (Minimum 2*degree): ";
    cin >> datapoints;
    // Error trap, cannot compute fit if not enough data
    while(datapoints<2*degree) {
        cout << "Error: Not enough data points" << endl;
        cout << "Please enter the number of data points you wish to enter (Minimum 2*degree): ";
        cin >> datapoints;
    }
    // Getting x's and settign up the x matrix
    xvals.init(1,datapoints);
    cout << "Please enter the x values:\n";
    for(int i=0; i<datapoints; i++) {
        cin >> val;
        xvals.setval(0,i,val);
    }
    // Getting y's and setting up the y matrix
    yvals.init(datapoints,1);
    cout << "Please enter the corresponding y values:\n";
    for(int i=0; i<datapoints; i++) {
        cin >> val;
        yvals.setval(i,0,val);
    }

    // Displaying input values
    cout << "V = " << endl;
    xvals.display();
    cout << "V = " << endl;
    yvals.display();

    // Setting up initial vandermonde
    V.init(datapoints,degree+1);
    for(int i=0; i<datapoints; i++) {
        for(int j=0; j<degree; j++) {
            V.setval(i,j,pow(xvals.getval(0,i),degree-j));
        }
    }
    // Displaying result
    cout << "V = " << endl;
    V.display();

    // Getting the transpose
    Vt = V.transpose();
    // Displaying result
    cout << "Vt = " << endl;
    Vt.display();

    // Vector multiplication
    VtV = Vt * V;
    // Displaying result
    cout << "VtV = " << endl;
    VtV.display();

    // Inversing
    iVtV = VtV.inverse();
    // Displaying result
    cout << "iVtV = " << endl;
    iVtV.display();

    // Finding coefficients
    c = (iVtV * Vt) * yvals;
    // Displaying result
    cout << "coefficients = " << endl;
    c.display();

    return 0;
}
