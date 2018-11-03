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
public class BplusTree implements Constantes
{
    No raiz;
    
    public BplusTree()
    {
        raiz = new No();
    }

    public No getRaiz() 
    {
        return raiz;
    } 
    
    public No navegarAteFolha(int info)
    {
        int i;
        No p = raiz;
        while(p.getvLig(0) != null)
        {
            i = 0;
            while(i < p.getTL() && info > p.getvInfo(i))
            {
                i = i+1;
            }
            p = p.getvLig(i);
        }
        return p;
    }
    
    public No localizarPai(No folha, int info)
    {
        No p, pai;
        int i;
        p = raiz;
        pai = p;
        while(p != folha)
        {
            i = 0;
            while(i < p.getTL() && info > p.getvInfo(i))
                i++;
            pai = p;
            p = p.getvLig(i);
        }
        return pai;
    }
    
    public void split(No no, No pai, int info)
    {
        No cx1, cx2;
        int i, pos;
        double aux = 0;
        int conta = 0;
        cx1 = new No();
        cx2 = new No();
        if(no.isFolha())
        {
            aux = N-1;
            conta = (int) Math.ceil((aux)/2);
            for (i = 0; i < conta; i++) 
            {
                cx1.setvInfo(no.getvInfo(i), i);
                cx1.setvPos(no.getvPos(i), i);
                cx1.setvLig(no.getvLig(i), i);
            }
            cx1.setvLig(no.getvLig(conta), conta);
            cx1.setTL(conta);
            
            for (i = conta; i < N+1; i++) 
            {
                cx2.setvInfo(no.getvInfo(i), i-conta);
                cx2.setvPos(no.getvPos(i), i-conta);
                cx2.setvLig(no.getvLig(i), i-conta);
            }
            cx2.setvLig(no.getvLig(no.getTL() - conta), no.getTL() - conta);
            cx2.setTL(no.getTL() - conta);
            
            cx1.setProx(cx2);
            cx2.setAnt(cx1);
            
            No avo = localizarPai(pai, info);
            int posAvo = pai.buscaPosNoPai(pai, avo);
            if(avo.getvLig(0) != null)
            {
                if(posAvo == 0)
                {
                    No auxLig = avo.getvLig(posAvo+1);
                    while(auxLig.getvLig(0) != null)
                        auxLig = auxLig.getvLig(0);

                    auxLig.setAnt(cx2);
                }
                else
                {
                    if(posAvo == avo.getTL())
                    {
                        No auxLig = avo.getvLig(posAvo-1);

                        while(auxLig.getvLig(auxLig.getTL()) != null)
                            auxLig = auxLig.getvLig(auxLig.getTL());

                        auxLig.setProx(cx1);
                    }

                    else
                    {
                        No auxLig = avo.getvLig(posAvo+1);
                        while(auxLig.getvLig(0) != null)
                            auxLig = auxLig.getvLig(0);

                        auxLig.setAnt(cx2);
                        //////////////////////////////////////
                        No auxLiga = avo.getvLig(posAvo-1);

                        while(auxLiga.getvLig(auxLiga.getTL()) != null)
                            auxLiga = auxLiga.getvLig(auxLiga.getTL());

                        auxLiga.setProx(cx1);
                    }
                }
            }
            
            
            if(pai == no)
            {
                no.setvInfo(no.getvInfo(conta), 0);
                no.setvPos(no.getvPos(conta), 0);
                no.setvLig(cx1, 0);
                no.setvLig(cx2, 1);
                no.setTL(1);              
            }
            else
            {
                info = no.getvInfo(conta);
                pos = pai.buscar(info);
                pai.remanejar(pos);
                pai.setTL(pai.getTL()+1);
                pai.setvInfo(no.getvInfo(conta), pos);
                pai.setvPos(no.getvPos(conta), pos);
                pai.setvLig(cx1, pos);
                pai.setvLig(cx2, pos+1);
                cx1.setProx(cx2);
                cx2.setAnt(cx1);
                if(pai.getTL() > N)
                {
                    no = pai;
                    info = no.getvInfo(conta);
                    pai = localizarPai(pai, info);
                    split(no, pai, info);
                }
            }           
        }
        else
        {
            conta = (int) Math.ceil(N/2-1);
            for (i = 0; i < conta; i++) 
            {
                cx1.setvInfo(no.getvInfo(i), i);
                cx1.setvPos(no.getvPos(i), i);
                cx1.setvLig(no.getvLig(i), i);
            }
            cx1.setvLig(no.getvLig(conta), conta);
            cx1.setTL(conta);
            
            for (i = conta; i < N-1; i++) 
            {
                cx2.setvInfo(no.getvInfo(i), i-conta);
                cx2.setvPos(no.getvPos(i), i-conta);
                cx2.setvLig(no.getvLig(i), i-conta);
            }
            cx2.setvLig(no.getvLig(no.getTL() - conta), no.getTL() - conta);
            cx2.setTL(no.getTL() - conta);
            
            if(pai == no)
            {
                no.setvInfo(no.getvInfo(conta), 0);
                no.setvPos(no.getvPos(conta), 0);
                no.setvLig(cx1, 0);
                no.setvLig(cx2, 1);
                no.setTL(1);
            }
            else
            {
                info = no.getvInfo(conta);
                pos = pai.buscar(info);
                pai.remanejar(pos);
                pai.setTL(pai.getTL()+1);
                pai.setvInfo(no.getvInfo(conta), pos);
                pai.setvPos(no.getvPos(conta), pos);
                pai.setvLig(cx1, pos);
                pai.setvLig(cx2, pos+1);
                if(pai.getTL() > N)
                {
                    no = pai;
                    info = no.getvInfo(conta);
                    pai = localizarPai(pai, info);
                    split(no, pai, info);
                }
            }
        }
        
        
    }
    
    public void inserir(int info, int posArq)
    {
       No folha, pai;
       int i, pos;
       
       if(raiz == null)
           raiz = new No(info, posArq);
       else
       {
           folha = navegarAteFolha(info);
           pos = folha.buscar(info);
           folha.remanejar(pos);
           folha.setTL(folha.getTL()+1);
           folha.setvInfo(info, pos);
           folha.setvPos(posArq, pos);
           if(folha.getTL() > N) 
           {
               pai = localizarPai(folha, info);
               split(folha, pai, info);
           }
       }
    }
    
    public void inOrdem(No raiz)
    {
        int i;
        if(raiz != null)
        {
            for (i = 0; i < raiz.getTL(); i++) 
            {
                inOrdem(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i)+" - ");
            }
            inOrdem(raiz.getvLig(i));
        }
    }
}
