package com.shpt.lib.kernel.models;

/**
 * Created by poovarasanv on 31/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 31/7/17 at 4:12 PM
 */

public class ModuleDetail {
    String moduleName;
    int version;

    public ModuleDetail(String moduleName, int version) {
        this.moduleName = moduleName;
        this.version = version;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
