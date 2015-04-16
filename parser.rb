require 'csv'
csv_contents = CSV.read('Ethnocentrism Strategy Counts.csv')
#Ignore first bit
20.times { csv_contents.shift }
open('Netlogo.csv', 'w') do |f|
	f.puts "CC,CD,DD,DC"
	csv_contents.each do |row|
		f.print row[1],",",row[5],",",row[13],",",row[9],"\n"
	end
end

