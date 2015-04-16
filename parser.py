import csv
with open('Ethnocentrism Strategy Counts.csv', 'rb') as csv_contents:
	reader = csv.reader(csv_contents, delimiter=',')

	#Ignore first bit
	for i in range(0,20):
		reader.next()
	
	f = open('Netlogo.csv', 'w')
	f.write("CC,CD,DD,DC\n")
	count = 0
	#Read crucial part of csv
	for row in reader:
		if count >= 1000:
			break
		count = count + 1
		f.write(row[1]+","+row[5]+","+row[13]+","+row[9]+"\n")