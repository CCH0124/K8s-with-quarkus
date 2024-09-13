{{- define "quarkus-dns.applicationTemplate" -}}
{{- with .Values.config.application }}
{{- toYaml . | nindent 2 }}
{{- end -}}
{{- end -}}
