% SPH4U0
% Bing Li
% Mr. van Bemmel
% Assignment 0
% 4.3.2 - Algebra Engine, part ii)

counter = 1; % Counter for aesthetics

syms x % Reusable symbol x

fprintf('PART VII: Integration by Substitution\n'); % Section divider for clarity

% Question 1
f = 200*(x^2+1)^99*x; % Declare an expression f
soln = simplify(int(f,x)); % Get the integral with respect to x using the int() function and then simply it
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln); % Output with formatting and add C
counter = counter+1; % Increment counter

% ------------------------------------------------------------
% The rest is the above 4 lines copy-pasted with differing f's
% ------------------------------------------------------------

% Question 2
f = 2*x*(x^2-1)^10;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 3
f = sin(x+5);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 4
f = cos(6*x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 5
f = (x/3-8)^(-5);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 6
f = 1/(1+16*x^2);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 7
f = 1/(1+2*x^2);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 8
f = 1/x + sec(pi*x)^2;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 9
f = sin(x)^2*cos(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 10
f = exp(sqrt(x))/sqrt(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 11
f = exp(x)/sqrt(1-exp(2*x));
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 12
f = cos(4*x)/(1-2*sin(4*x))^4;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 13
f = x^2*sqrt(x-1);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 14
f = cos(x)^3;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 15
f = 1/(sym('a')^2+x^2);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 16
f = 1/sqrt(2-x^2);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 17
f = x/(1+x^2);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 18
f = 5*x*sqrt(1-x^2);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 19
f = x^3/sqrt(x^4+12);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 20
f = cos(x)/(5+sin(x))^2;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% END of part VII

fprintf('\nPART VIII: Integration by Parts\n'); % Section divider for clarity
counter = 1; % Reset counter to start counting from question 1 again

% Question 21
f = 2*x*4*x^3;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 22
f = x*cos(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 23
f = x*exp(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 24
f = log(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 25
f = x^2*exp(-x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 26
f = exp(x)*cos(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 27
f = x*log(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 28
f = log(x)/x^5;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 29
f = asin(3*x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 30
f = x^2*exp(3*x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 31
f = x^3*log(5*x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 32
f = log(x)^2;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 33
f = log(x)^3;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 34
f = x*sqrt(x+3);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 35
f = (log(x)/x)^2;
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 36
f = 2*x*atan(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 37
f = x*sin(x)*cos(x);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 38
f = x^5*exp(x^3);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 39
f = x^3*cos(x^2);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% Question 40
f = x^7*sqrt(5+3*x^4);
soln = simplify(int(f,x));
fprintf('%i.\tdy/dx %s \n =\t%s + C\n', counter, f, soln);
counter = counter+1;

% END of part VIII
