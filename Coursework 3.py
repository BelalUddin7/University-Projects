# Name of program (STUDENT MARKS CALCULATOR)
# Author of the program (Muhammod Belal Uddin)
# Date programmed (Start - 02/04/2024) (End - 10/04/2024)
# Purpose of the program (Part 2) (Adding more secondary functionality)

# Function to collect student marks from user input
def student_marks():
    marks = []  # Initialize an empty list to store the marks
    while True:  # Infinite loop until user enters 'done'
        mark_input = input("Enter mark(s) separated by commas (or 'done' to finish): ")  # Prompt user for input
        if mark_input.lower() == 'done':  # Confirm if user wants to proceed
            break  # Exit the loop if 'done' is entered

        # Split the input string by commas and iterate through each part
        for mark_str in mark_input.split(','):
            mark_str = mark_str.strip()  # Remove leading/trailing whitespace
            try:
                marks.append(float(mark_str))  # Convert the input to float and append to the marks list
            except ValueError:
                print(f"Invalid input: '{mark_str}'. Please enter a valid number.")  # Notify user if input is not a valid number
    return marks  # Return the list of marks

# Function to read marks from a file
def read_marks_from_file():
    file_path = input("Enter the file path: ")  # Prompt user to enter file path
    try:
        with open(file_path, "r") as file:
            marks = [float(mark.strip()) for mark in file.readlines()]  # Read marks from file
            return marks
    except FileNotFoundError:
        print("File not found or path is incorrect.")
        return []
    except IOError:
        print("An error occurred while reading the file.")
        return []

# Function to allow the user to enter a new set of numbers
def enter_new_numbers():
    marks = []  # Initialize an empty list to store the marks
    while True:  # Infinite loop until user enters 'done'
        mark = input("Enter a mark (or type 'done' to finish): ")  # Prompt user for input
        if mark.lower() == 'done':  # Check if user wants to finish
            break  # Exit the loop if 'done' is entered
        else:
            try:
                marks.append(float(mark))  # Convert the input to float and append to the marks list
            except ValueError:
                print("Please enter a valid number.")  # Notify user if input is not a valid number
    print("You have entered", len(marks), "marks.")  # Inform user about the number of marks entered
    return marks  # Return the list of marks

# Function to allow the user to enter more numbers
def enter_more_numbers(marks):
    while True:
        mark_input = input("Enter more mark(s) separated by commas (or 'done' to finish): ")
        if mark_input.lower() == 'done':
            break

        for mark_str in mark_input.split(','):
            mark_str = mark_str.strip()
            try:
                marks.append(float(mark_str))
            except ValueError:
                print(f"Invalid input: '{mark_str}'. Please enter a valid number.")

# Function to allow the user to exit the application
def exit_application():
    print("Exiting the application.")  # Notify user about exiting
    exit()  # Exit the program

# Printing the mode of the marks
def calculate_mode(marks):
    if not marks:  # Check if no marks are entered
        print("No marks entered.")  # Notify user if no marks are entered
        return

    # Count occurrences of each mark
    mark_counts = {}  # Initialize an empty dictionary to store mark counts
    for mark in marks:  # Iterate through the marks list
        if mark in mark_counts:
            mark_counts[mark] += 1  # Increment count if mark already exists in dictionary
        else:
            mark_counts[mark] = 1  # Initialize count to 1 if mark is encountered for the first time

    # Find the mode(s)
    max_count = max(mark_counts.values())  # Find the maximum count
    modes = [mark for mark, count in mark_counts.items() if count == max_count]  # Get marks with maximum count

    if len(modes) == 1:  # Check if there is only one mode
        print("Mode of the numbers:", modes[0])  # Print the mode
    else:
        print("Modes of the numbers:", ", ".join(str(mode) for mode in modes))  # Print multiple modes if exist

# Function to calculate skewness
def calculate_skewness(marks):
    if not marks:  # Check if no marks are entered
        return 0  # Return 0 if no marks are entered

    n = len(marks)
    mean = sum(marks) / n  # Calculate the mean

    # Step 2: Calculate the median
    sorted_marks = sorted(marks)
    if n % 2 == 0:
        median = (sorted_marks[n // 2 - 1] + sorted_marks[n // 2]) / 2
    else:
        median = sorted_marks[n // 2]

    # Step 3: Calculate the standard deviation
    squared_deviations = [(x - mean) ** 2 for x in marks]
    variance = sum(squared_deviations) / n
    std_dev = variance ** 0.5

    # Calculate the skewness
    skewness = (mean - median) ** 3 / std_dev

    return skewness

# Printing the mean of the marks
def calculate_mean(marks):
    if not marks:  # Check if no marks are entered
        return 0  # Return 0 if no marks are entered
    return sum(marks) / len(marks)  # Calculate mean and return

# Printing the median of the marks
def calculate_median(marks):
    sort_marks = sorted(marks)  # Sort the marks in ascending order
    n = len(sort_marks)  # Get the number of marks
    if n % 2 == 0:  # Check if the number of marks is even
        middle_index = n // 2  # Calculate the middle index
        median = (sort_marks[middle_index - 1] + sort_marks[middle_index]) / 2  # Calculate the median
    else:
        median = sort_marks[n // 2]  # If the number of marks is odd, median is the middle value
    return median  # Return the median

# Loop to present the menu and handle user input
def main():
    global marks_list
    while True:  # Infinite loop until user chooses to exit
        if len(marks_list) < 2:
            print("You must enter at least two numbers before accessing the main menu.")
            marks_list = student_marks()
        else:
            print("\nOptions:")  # Display options to the user
            print("1. Print mean")
            print("2. Print median")
            print("3. Print mode")
            print("4. Print skewness")
            print("5. Enter new set of numbers")
            print("6. Enter more numbers")
            print("7. Read marks from file")
            print("8. Exit")

            choice = input("Enter your choice: ")  # Prompt user for choice

            if choice == '1':  # If user chooses option 1
                print("Mean:", calculate_mean(marks_list[:]))  # Calculate and print mean
            elif choice == '2':  # If user chooses option 2
                print("Median:", calculate_median(marks_list[:]))  # Calculate and print median
            elif choice == '3':  # If user chooses option 3
                calculate_mode(marks_list[:])  # Calculate and print mode
            elif choice == '4':  # If user chooses option 4
                print("Skewness:", calculate_skewness(marks_list[:]))  # Calculate and print skewness
            elif choice == '5':  # If user chooses option 5
                marks_list = enter_new_numbers()  # Allow user to enter new set of numbers and update marks_list
            elif choice == '6':  # If user chooses option 6
                enter_more_numbers(marks_list)  # Allow user to enter more numbers to existing list
            elif choice == '7':  # If user chooses option 7
                marks_list = read_marks_from_file()  # Read marks from file and update marks_list
            elif choice == '8':  # If user chooses option 8
                exit_application()  # Exit the application
            else:
                print("Invalid choice. Please try again.")  # Notify user if choice is invalid

# Running main function
if __name__ == "__main__":
    marks_list = []  # Initialize global variable 'marks_list'
    main()
