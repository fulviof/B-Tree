/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b.tree;

/**
 *
 * @author fulviofanelli
 */
public class No implements Constantes {
    
    private int vInfo[];
    private int vPos[];
    private No vLig[];
    private No prox;
    private No ant;
    int TL;
    
    public No()
    {
       vInfo = new int[N+1];
       vPos = new int[N+1];
       vLig = new No[N+2];
       prox = null;
       ant = null;
       int TL = 0;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }
    
    public No getDir(No pai, int posFolha)
    {
        return pai.getvLig(posFolha+1);
    }
    
    public No getEsq(No pai, int posFolha)
    {
        return pai.getvLig(posFolha-1);
    }
    
    public No(int info, int posArq)
    {
       this();
       vInfo[TL] = info;
       vPos[TL++] = posArq;    
    }

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int info, int pos) {
        this.vInfo[pos] = info;
    }

    public int getvPos(int pos) {
        return vPos[pos];
    }

    public void setvPos(int info, int pos) {
        this.vPos[pos] = info;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(No info, int pos) {
        this.vLig[pos] = info;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }
    
    public void remanejar(int pos)
    {
        vLig[TL+1] = vLig[TL];
        for (int j = TL; j>pos; j--) 
        {
            vInfo[j] = vInfo[j-1];
            vPos[j] = vPos[j-1];
            vLig[j] = vLig[j-1];
        }
    }
    
    public void remanejarEx(int pos)
    {
        vLig[TL+1] = vLig[TL];
        for (int j = pos; j<TL-1; j++) 
        {
            vInfo[j] = vInfo[j+1];
            vPos[j] = vPos[j+1];
            vLig[j] = vLig[j+1];
        }
    }
    
    public int buscar(int info)
    {
        int i = 0;
        
        while(i < TL && info > vInfo[i])
            i = i+1;
        
        return i;
    }
    
    public int buscaPosNoPai(No no, No pai)
    {
        int posPai = 0;
        while(posPai < pai.getTL() && no != pai.getvLig(posPai))
            posPai++;
        
        return posPai;
    }
    
    public boolean isFolha()
    {
        return getvLig(0) == null;
    }

}
