% SPH4U0
% Bing Li
% Mr. van Bemmel
% Assignment 0
% 4.3.4.2 - Fourier Series

x = linspace(0,2,200000); % Set a range for the x values, and generate a lot of points
fourier = 4/pi * sin(pi*x); % Start the function with 1 term

% Go up to 640 terms
for n = 2:640
    fourier = fourier + (4/pi * 1/(2*n-1) * sin((2*n-1)*pi*x)); % Add the next term to the function
    
    plot(x,fourier) % Draw the graph
    axis([0,2,-1.2,1.2]) % Axes settings [x-min,x-max,y-min,y-max]
    
    drawnow % Update screen
end
