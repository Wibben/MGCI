% SPH4U0
% Bing Li
% Mr. van Bemmel
% Assignment 0
% 4.3.2 - Algebra Engine, part i)

counter = 1; % Counter for aesthetics

syms x % Reusable symbol x

fprintf('PART I: Basic Derivatives\n'); % Section divider for clarity

% Question 1
f = x^2 + 4*x + 3; % Declare an expression f
soln = simplify(diff(f)); % Get the derivative using the diff() function and then simply it
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln); % Output with formatting
counter = counter+1; % Increment counter

% ------------------------------------------------------------
% The rest is the above 4 lines copy-pasted with differing f's
% ------------------------------------------------------------

% Question 2
f = 4*x^3 - 3*x^2 + 2*x - pi;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 3
f = (x^2)/3 - 3/(x^2);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 4
f = sqrt(x) - 1/(sqrt(x));
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 5
f = (x+1)/(x-2);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 6
f = (x^2-2)/(x^2);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 7
f = (x^2)/(x^2-2);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 8
f = sqrt(x)*(x^2+1);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 9
f = exp(x)/(exp(x)-1);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 10
f = 2/sqrt(x) + sqrt(x)/2;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 11
f = (2*x)/(x-1);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 12
f = (3*x-2)*(2*x+1);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 13
f = 5*x^2 - 5*sqrt(x) - 3/x;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 14
f = sqrt(x)/(sqrt(x)-1);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 15
f = exp(x)/x;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 16
f = 6*x^(-3/2) + 7*x^(1/5) + 1;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 17
f = -7/(1-x^3);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 18
f = 4/3*(x^(3/4-sym('pi')));
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 19
f = 1/(7*x);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 20
f = 2*x^(1/2-exp(1));
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% END of part I

fprintf('\nPART II: Trignometric Derivatives\n'); % Section divider for clarity
counter = 1; % Reset counter to start counting from question 1 again

% Question 21
f = cos(sqrt(x))^3;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 22
f = (cos(x)/(1-sin(x)))^2;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 23
f = exp(2*x)*sin(3*x);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 24
f = sqrt(sin(x));
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 25
f = tan(x)/(x^2-1);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 26
f = asin(x^2);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 27
f = (x^2+1)*atan(x);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 28
f = acos(x)^3;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 29
f = tan(6*x);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 30
f = sin(2*x)/cos(2*x);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 31
f = sin(x)/(x^2);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 32
f = tan(sin(x)) + 1/sym('pi');
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 33
f = 3*cos(5*x) + 3*sin(x^9);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 34
f = sin(3*x^2-2*x+1)^3;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 35
f = x^2*tan(1/x);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 36
f = sin(sqrt(x))^2;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 37
f = exp(3*x)*cos(2*x);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 38
f = asin(x^3)^4;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 39
f = tan(6*x^2-1);
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% Question 40
f = (sec(x)^2-tan(x)^2)/x^3;
soln = simplify(diff(f));
fprintf('%i.\tdy/dx %s \n =\t%s\n', counter, f, soln);
counter = counter+1;

% END of part II
