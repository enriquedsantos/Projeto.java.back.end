package view;
import javax.swing.*;
import java.nio.file.*;


public class OperacaoDeArquivos {
    public static void main(String[] args) {
        try {
            String[] options = {"Copiar", "Mover", "Deletar", "Renomear"};
            int choice = JOptionPane.showOptionDialog(null, "Escolha a operação:", "Operações de Arquivo",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Selecione o arquivo.");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = chooser.showOpenDialog(null);
            String fileFullPath = "";

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                System.out.println("Você escolheu o arquivo: " + chooser.getSelectedFile().getName());
            } else {
                System.out.println("Operação cancelada.");
                return;
            }

            switch (choice) {
                case 0: 
                    chooser.setDialogTitle("Selecione a pasta de destino para copiar.");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        Path pathOrigin = Paths.get(fileFullPath);
                        Path pathDestination = Paths.get(chooser.getSelectedFile().getAbsolutePath(), chooser.getSelectedFile().getName());
                        Files.copy(pathOrigin, pathDestination, StandardCopyOption.REPLACE_EXISTING);
                        JOptionPane.showMessageDialog(null, "Arquivo copiado com sucesso!");
                    }
                    break;

                case 1: 
                    chooser.setDialogTitle("Selecione a pasta de destino para mover.");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        Path pathOrigin = Paths.get(fileFullPath);
                        Path pathDestination = Paths.get(chooser.getSelectedFile().getAbsolutePath(), chooser.getSelectedFile().getName());
                        Files.move(pathOrigin, pathDestination, StandardCopyOption.REPLACE_EXISTING);
                        JOptionPane.showMessageDialog(null, "Arquivo movido com sucesso!");
                    }
                    break;

                case 2:
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o arquivo?");
                    if (confirmDelete == JOptionPane.YES_OPTION) {
                        Path pathToDelete = Paths.get(fileFullPath);
                        Files.delete(pathToDelete);
                        JOptionPane.showMessageDialog(null, "Arquivo deletado com sucesso!");
                    }
                    break;

                case 3: 
                    String newName = JOptionPane.showInputDialog("Digite o novo nome:");
                    if (newName != null && !newName.isEmpty()) {
                        Path pathSource = Paths.get(fileFullPath);
                        Path pathRenamed = pathSource.resolveSibling(newName);
                        Files.move(pathSource, pathRenamed);
                        JOptionPane.showMessageDialog(null, "Arquivo renomeado com sucesso!");
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage());
        }
    }
}
