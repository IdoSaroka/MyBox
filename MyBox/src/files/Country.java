package files;


public class Country 
{
    private String name;
    private String flagIcon;

    public Country(String name, String flagIcon) {
        this.name = name;
        this.flagIcon = flagIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(String flagIcon) {
        this.flagIcon = flagIcon;
    }
}
