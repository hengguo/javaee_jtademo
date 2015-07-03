package demo.velocity;

import java.util.ArrayList;
import java.util.List;

public class Row {
	private String GSXX01;
	private String KCLX;
	private String SPFL01;
	private String SPFLMC;
	private String CK01;
	private String CKMC;
    private String ZDDM;
    private String ZDMC;
    private List<Rowjqm> rowjqms = new ArrayList<Rowjqm>();
    /**
     * 获取gSXX01
     * @return gSXX01
     */
    public String getGSXX01() {
        return GSXX01;
    }
    /**
     * 设置gSXX01
     * @param gSXX01 gSXX01
     */
    public void setGSXX01(String gSXX01) {
        GSXX01 = gSXX01;
    }
    /**
     * 获取kCLX
     * @return kCLX
     */
    public String getKCLX() {
        return KCLX;
    }
    /**
     * 设置kCLX
     * @param kCLX kCLX
     */
    public void setKCLX(String kCLX) {
        KCLX = kCLX;
    }
    /**
     * 获取sPFL01
     * @return sPFL01
     */
    public String getSPFL01() {
        return SPFL01;
    }
    /**
     * 设置sPFL01
     * @param sPFL01 sPFL01
     */
    public void setSPFL01(String sPFL01) {
        SPFL01 = sPFL01;
    }
    /**
     * 获取sPFLMC
     * @return sPFLMC
     */
    public String getSPFLMC() {
        return SPFLMC;
    }
    /**
     * 设置sPFLMC
     * @param sPFLMC sPFLMC
     */
    public void setSPFLMC(String sPFLMC) {
        SPFLMC = sPFLMC;
    }
    /**
     * 获取cK01
     * @return cK01
     */
    public String getCK01() {
        return CK01;
    }
    /**
     * 设置cK01
     * @param cK01 cK01
     */
    public void setCK01(String cK01) {
        CK01 = cK01;
    }
    /**
     * 获取cKMC
     * @return cKMC
     */
    public String getCKMC() {
        return CKMC;
    }
    /**
     * 设置cKMC
     * @param cKMC cKMC
     */
    public void setCKMC(String cKMC) {
        CKMC = cKMC;
    }
    /**
     * 获取zDDM
     * @return zDDM
     */
    public String getZDDM() {
        return ZDDM;
    }
    /**
     * 设置zDDM
     * @param zDDM zDDM
     */
    public void setZDDM(String zDDM) {
        ZDDM = zDDM;
    }
    /**
     * 获取zDMC
     * @return zDMC
     */
    public String getZDMC() {
        return ZDMC;
    }
    /**
     * 设置zDMC
     * @param zDMC zDMC
     */
    public void setZDMC(String zDMC) {
        ZDMC = zDMC;
    }
    /**
     * 获取rowjqms
     * @return rowjqms
     */
    public List<Rowjqm> getRowjqms() {
        return rowjqms;
    }
    /**
     * 设置rowjqms
     * @param rowjqms rowjqms
     */
    public void setRowjqms(List<Rowjqm> rowjqms) {
        this.rowjqms = rowjqms;
    }
    public void addRowjqm(Rowjqm rowjqm){
        this.rowjqms.add(rowjqm);
    }
	
}