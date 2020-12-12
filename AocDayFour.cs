using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

class AocDayFour
{
	public static void Main()
	{
		AocDayFour obj = new AocDayFour();
		var puzzle = obj.InputPuzzle();
		obj.CountValidPuzzles(puzzle);
	}

	List<string> InputPuzzle()
	{
		string line;
		var input = new List<string>();
		while ((line = Console.ReadLine()) != null)
		{
			input.Add(line);
		}
		return input;
	}
	
	bool ValidatePasswordFields(Dictionary<string, string> password) 
	{
	    return (password.Keys.Count == 8 || (password.Keys.Count == 7 && !password.ContainsKey("cid")));
	}
	
	bool ValidatePasswordData(Dictionary<string, string> password) 
	{
	    bool validFlag = true;
	    foreach (string key in password.Keys)
		{
		    if (!IsFieldValid(key, password[key]))
				validFlag = false;
		}
		return validFlag;
	}
	
	void EnterPasswordDataIntoMap(Dictionary<string, string> password, string input) 
	{
	    string[] keyValuePairs = input.Split(' ');
		for (int i = 0; i < keyValuePairs.Length; i++)
		{
			string[] keyValue = keyValuePairs[i].Split(':');
			password[keyValue[0]] = keyValue[1];
		}
	}

	void CountValidPuzzles(List<string> input)
	{
		int countExcludingDataValidation = 0;
		int countIncludingDataValidation = 0;
		Dictionary<string, string> fields = new Dictionary<string, string>();
		foreach (string line in input)
		{
			if (String.IsNullOrEmpty(line))
			{
			    if(ValidatePasswordFields(fields)) 
			    {
			        countExcludingDataValidation++;
			        if(ValidatePasswordData(fields)) 
			            countIncludingDataValidation++;
			    }
				fields = new Dictionary<string, string>();
			}
			else
			{
				EnterPasswordDataIntoMap(fields, line);
			}
		}
		Console.WriteLine(countExcludingDataValidation);
		Console.WriteLine(countIncludingDataValidation);
	}
	
    bool ValidateByr(int value) 
    {
        return (value >= 1920 && value <= 2002);
    }
    
    bool ValidateIyr(int value) 
    {
        return (value >= 2010 && value <= 2020);
    }
    
    bool ValidateEyr(int value) 
    {
        return (value >= 2020 && value <= 2030);
    }
    
    bool ValidateHcl(string value) 
    {
        return (Regex.Match(value, "#[0-9a-f]{6}").Success);    
    }
    
    bool ValidatePid(string value) 
    {
        return (value.Length == 9);
    }
    
    bool ValidateEcl(string value) 
    {
        return (value.Equals("amb") || value.Equals("blu") || value.Equals("brn") || value.Equals("gry") || value.Equals("grn") || value.Equals("hzl") || value.Equals("oth"));
    }
    
    bool ValidateHgt(string value) 
    {
        if(value.EndsWith("cm")) 
        {
            int integerValue = Convert.ToInt32(value.Substring(0, value.Length - 2));
            if (integerValue >= 150 && integerValue <= 193)
                return true;
        }
        else if(value.EndsWith("in")) 
        {
            int integerValue = Convert.ToInt32(value.Substring(0, value.Length - 2));
            if (integerValue >= 59 && integerValue <= 76)
                return true;
        }
        return false;
    }
    
	bool IsFieldValid(string key, string value)
	{
		bool isValid = false;
		switch (key)
		{
			case "byr":
				isValid = ValidateByr(Convert.ToInt32(value));
				break;
			case "iyr":
			    isValid = ValidateIyr(Convert.ToInt32(value));
				break;
			case "eyr":
				isValid = ValidateEyr(Convert.ToInt32(value));
				break;
			case "hgt":
				isValid = ValidateHgt(value);
				break;
			case "hcl":
				isValid = ValidateHcl(value);
				break;
			case "ecl":
				isValid = ValidateEcl(value);
				break;
			case "pid":
				isValid = ValidatePid(value);
				break;
			case "cid":
				isValid = true;
				break;
			default:
			    isValid = false;
			    break;
		}
		return isValid;
	}
}
